package IIS.Server.api.management.seat;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestvike.linq.Linq;

import IIS.Server.api.BaseController;
import IIS.Server.api.PriceCategoryEnum;
import IIS.Server.api.Response;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponse;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.seat.requests.*;
import IIS.Server.api.management.seat.responses.*;
import IIS.Server.api.management.seat_row.requests.GetSeatRowRequest;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Reservation;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;
import generated.cinemaService.proxies.ICinemaHall;
import generated.cinemaService.proxies.ISeat;
import generated.cinemaService.proxies.ISeatRow;

@RestController
@RequestMapping(path="/management/seat", produces="application/json")
@CrossOrigin(origins="*")
public class SeatController extends BaseController 
{    
    @PostMapping("/list")
    public ResponseEntity<GetSeatsResponse> listSeats(@RequestBody GetSeatsRequest request) 
    {
        return isolated(() -> 
        {
            if (!CinemaService.getInstance().getSeatRowCache().containsKey(request.getRowId())) {
                GetSeatsResponse response = new GetSeatsResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find row for id " + request.getRowId()));
                return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.BAD_REQUEST);
            }

            GetSeatsResponse response = new GetSeatsResponse();
            response.setSeats(ObjectX.createFromMany(CinemaService.getInstance().getSeatRow(request.getRowId()).getSeats(), GetSeatsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSeatResponse> createSeat(@RequestBody CreateSeatRequest request) 
    {
        return isolated(() -> 
        {
            final var seatRowProxy = CinemaService.getCacheOf(ISeatRow.class).getOrDefault(request.getRowId(), null);
            if (seatRowProxy == null) 
            {
                return Response.error(CreateSeatResponse.class, "Could not find row for id " + request.getRowId());
            }
            try 
            {
                final SeatRow row = seatRowProxy.getTheObject();
                Seat.createFresh(request.getName(), row);
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            CreateSeatResponse response = new CreateSeatResponse();
            response.setSuccess(true);
            return new ResponseEntity<CreateSeatResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatResponse> updateSeat(@RequestBody UpdateSeatRequest request) 
    {
        return isolated(() -> 
        {
            final var seatProxy = CinemaService.getCacheOf(ISeat.class).getOrDefault(request.getId(), null);
            if (seatProxy == null) 
            {
                return Response.error(UpdateSeatResponse.class, "Could not find seat for id " + request.getId());
            }
            try 
            {
                final Seat seat = seatProxy.getTheObject();
                seat.setName(request.getName());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }
            UpdateSeatResponse response = new UpdateSeatResponse();
            response.setSuccess(true);
            return new ResponseEntity<UpdateSeatResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetSeatsFullResponse> listDetailedSeats() 
    {
        return isolated(() -> 
        {
            final var seats = Linq.of(CinemaService.getSetOf(ISeat.class))
                .select(s ->
                {
                    final var seat = ObjectX.createFrom(s, GetSeatsFullResponseEntry.class);
                    final var r = s.getRow();
                    final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                    row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                    row.setSeatCount(r.getSeats().size());
                    seat.setRow(row);
                    return seat;
                })
                .toList();

            GetSeatsFullResponse response = new GetSeatsFullResponse();
            response.setSeats(seats);
            response.setSuccess(true);
            return new ResponseEntity<GetSeatsFullResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatResponse> deleteSeat(@RequestBody DeleteSeatRequest request) 
    {
        return isolated(() -> 
        {
            final var seat = CinemaService.getCacheOf(ISeat.class).getOrDefault(request.getId(), null);
            if (seat == null) 
            {
                return Response.error(DeleteSeatResponse.class, "Could not find seat for id " + request.getId());
            }
            try 
            {
                for (final BookingState booking : seat.getBookings()) 
                {
                    if (booking instanceof Booking)
                    {
                        Booking.delete(booking.getId());
                    }
                    else
                    {
                        Reservation.delete(booking.getId());
                    }
                }
                Seat.delete(seat.getId());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            DeleteSeatResponse response = new DeleteSeatResponse();
            response.setSuccess(true);
            return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.OK);
        });
    }

    /**
     * Get all seat rows of a given cinema hall.
     * @param request
     * @return
     */
    @PostMapping("/available-rows")
    public ResponseEntity<GetSeatRowsResponse> getAvailableRows(@RequestBody GetSeatRowRequest request) 
    {
        return isolated(() -> 
        {
            final var hall = CinemaService.getCacheOf(ICinemaHall.class).getOrDefault(request.getCinemaHallId(), null);
            if (hall == null)
            {
                return Response.error(GetSeatRowsResponse.class, "Could not find cinema hall for id " + request.getCinemaHallId());
            }
            final var result = Linq.of(hall.getRows())
                .select(r -> 
                {
                    final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                    row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                    row.setSeatCount(r.getSeats().size());
                    return row;
                })
                .toList();

            GetSeatRowsResponse response = new GetSeatRowsResponse();
            response.setRows(result);
            response.setSuccess(true);
            return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
        });
    }

    /**
     * Get all cinema halls which are available.
     * @return
     */
    @GetMapping("/available-halls")
    public ResponseEntity<GetCinemaHallsResponse> getAvailableHalls() 
    {
        return isolated(() -> 
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getSetOf(ICinemaHall.class), GetCinemaHallsResponseEntry.class));
            response.setSuccess(true);
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
    }
}
