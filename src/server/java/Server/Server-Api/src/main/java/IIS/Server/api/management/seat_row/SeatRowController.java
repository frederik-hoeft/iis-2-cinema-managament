package IIS.Server.api.management.seat_row;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.BaseController;
import IIS.Server.api.PriceCategoryEnum;
import IIS.Server.api.Response;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponse;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.seat.responses.GetSeatsResponseEntry;
import IIS.Server.api.management.seat_row.requests.CreateSeatRowRequest;
import IIS.Server.api.management.seat_row.requests.DeleteSeatRowRequest;
import IIS.Server.api.management.seat_row.requests.GetSeatRowRequest;
import IIS.Server.api.management.seat_row.requests.UpdateSeatRowRequest;
import IIS.Server.api.management.seat_row.responses.CreateSeatRowResponse;
import IIS.Server.api.management.seat_row.responses.DeleteSeatRowResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsFullResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsFullResponseEntry;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.api.user.account.responses.UpdateSeatRowResponse;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Reservation;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;
import generated.cinemaService.proxies.ICinemaHall;
import generated.cinemaService.proxies.ISeatRow;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/seat-row", produces="application/json")
@CrossOrigin(origins="*")
public class SeatRowController extends BaseController 
{
    @PostMapping("/list")
    public ResponseEntity<GetSeatRowsResponse> listSeatRows(@RequestBody GetSeatRowRequest request) 
    {
        return scheduled(() -> 
        {
            final var hall = CinemaService.getCacheOf(ICinemaHall.class).getOrDefault(request.getCinemaHallId(), null);
            if (hall == null) 
            {
                return Response.error(GetSeatRowsResponse.class, "Could not find cinema hall for id " + request.getCinemaHallId());
            }
            GetSeatRowsResponse response = new GetSeatRowsResponse();
            List<GetSeatRowsResponseEntry> seatRows = new ArrayList<GetSeatRowsResponseEntry>();
            for (SeatRow seatRow : hall.getRows()) {
                GetSeatRowsResponseEntry entry = ObjectX.createFrom(seatRow, GetSeatRowsResponseEntry.class);
                entry.setSeatCount(seatRow.getSeats().size());
                entry.setPrice(PriceCategoryEnum.from(seatRow.getPrice()));
                seatRows.add(entry);
            }
            response.setRows(seatRows);
            response.setSuccess(true);
            return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetSeatRowsFullResponse> listDetailedSeatRows() 
    {
        return scheduled(() -> 
        {
            GetSeatRowsFullResponse response = new GetSeatRowsFullResponse();
            List<GetSeatRowsFullResponseEntry> seatRows = new ArrayList<GetSeatRowsFullResponseEntry>();
            for (ICinemaHall hall : CinemaService.getSetOf(ICinemaHall.class))
            for (SeatRow seatRow : hall.getRows()) {
                GetSeatRowsFullResponseEntry entry = ObjectX.createFrom(seatRow, GetSeatRowsFullResponseEntry.class);
                entry.setCinemaHallName(hall.getName());
                entry.setSeats(ObjectX.createFromMany(seatRow.getSeats(), GetSeatsResponseEntry.class));
                entry.setPrice(PriceCategoryEnum.from(seatRow.getPrice()));
                seatRows.add(entry);
            }
            response.setRows(seatRows);
            response.setSuccess(true);
            return new ResponseEntity<GetSeatRowsFullResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSeatRowResponse> createSeatRow(@RequestBody CreateSeatRowRequest request) 
    {
        return scheduled(() -> 
        {
            final var hall = CinemaService.getCacheOf(ICinemaHall.class).getOrDefault(request.getCinemaHallId(), null);
            if (hall == null)
            {
                return Response.error(CreateSeatRowResponse.class, "Could not find cinema hall for id " + request.getCinemaHallId());
            }
            try 
            {
                SeatRow.createFresh(request.getPriceCategory().toMpsUnsafe(), request.getName(), hall.getTheObject());
            }
            catch (PersistenceException e) 
            {
                return Response.error(e);
            }
            CreateSeatRowResponse response = new CreateSeatRowResponse();
            response.setSuccess(true);
            return new ResponseEntity<CreateSeatRowResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatRowResponse> updateSeatRow(@RequestBody UpdateSeatRowRequest request) 
    {
        return scheduled(() -> 
        {
            if (!CinemaService.getInstance().getSeatRowCache().containsKey(request.getId())) {
                UpdateSeatRowResponse response = new UpdateSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find seat row for id " + request.getId()));
                return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                SeatRow seatRow = CinemaService.getInstance().getSeatRow(request.getId());
                seatRow.setName(request.getName());
                seatRow.setPrice(request.getPriceCategory().toMpsUnsafe());
            }
            catch (PersistenceException e) {
                UpdateSeatRowResponse response = new UpdateSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateSeatRowResponse response = new UpdateSeatRowResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatRowResponse> deleteSeatRow(@RequestBody DeleteSeatRowRequest request) 
    {
        return scheduled(() -> 
        {
            final var seatRow = CinemaService.getCacheOf(ISeatRow.class).getOrDefault(request.getId(), null);
            if (seatRow == null) 
            {
                return Response.error(DeleteSeatRowResponse.class, "Could not find seat row for id " + request.getId());
            }
            try 
            {
                for (final Seat seat : seatRow.getSeats()) 
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
                SeatRow.delete(seatRow.getId());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            DeleteSeatRowResponse response = new DeleteSeatRowResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/available-halls")
    public ResponseEntity<GetCinemaHallsResponse> getAvailableHalls() 
    {
        return scheduled(() -> 
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getSetOf(ICinemaHall.class), GetCinemaHallsResponseEntry.class));
            response.setSuccess(true);
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
    }
}
