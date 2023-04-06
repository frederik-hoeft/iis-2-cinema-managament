package IIS.Server.api.management.cinema_hall;

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
import IIS.Server.api.management.cinema_hall.requests.*;
import IIS.Server.api.management.cinema_hall.responses.*;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Reservation;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;
import generated.cinemaService.proxies.ICinemaHall;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/cinema-hall", produces="application/json")
@CrossOrigin(origins="*")
public class CinemaHallController extends BaseController {
    
    @GetMapping("/list")
    public ResponseEntity<GetCinemaHallsResponse> listCinemaHalls() 
    {
        return isolated(() ->
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values(), GetCinemaHallsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetCinemaHallsFullResponse> listDetailedCinemaHalls() 
    {
        return isolated(() -> 
        {
            final var result = Linq.of(CinemaService.getSetOf(ICinemaHall.class))
                .select(h -> 
                {
                    final var hall = ObjectX.createFrom(h, GetCinemaHallsFullResponseEntry.class);
                    hall.setRows(Linq.of(h.getRows()).select(r -> 
                    {
                        final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                        row.setSeatCount(r.getSeats().size());
                        row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                        return row;
                    }).toList());
                    return hall;
                })
                .toList();
            GetCinemaHallsFullResponse response = new GetCinemaHallsFullResponse();
            response.setCinemaHalls(result);
            response.setSuccess(true);
            return new ResponseEntity<GetCinemaHallsFullResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/create")
    public ResponseEntity<CreateCinemaHallResponse> createCinemaHall(@RequestBody CreateCinemaHallRequest request) {

        return isolated(() -> 
        {
            try {
                CinemaHall.createFresh(request.getAvailable(), request.getName());
            }
            catch (PersistenceException e) {
                CreateCinemaHallResponse response = new CreateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<CreateCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateCinemaHallResponse response = new CreateCinemaHallResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateCinemaHallResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateCinemaHallResponse> updateCinemaHall(@RequestBody UpdateCinemaHallRequest request) {

        return isolated(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getId())) {
                UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getId()));
                return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                CinemaHall cinemaHall = CinemaService.getInstance().getCinemaHall(request.getId());
                cinemaHall.setName(request.getNewName());
                cinemaHall.setAvailable(request.getAvailable());
            }
            catch (PersistenceException e) {
                UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteCinemaHallResponse> deleteCinemaHall(@RequestBody DeleteCinemaHallRequest request) {

        return isolated(() -> 
        {
            final var cinemaHall = CinemaService.getCacheOf(ICinemaHall.class).getOrDefault(request.getId(), null);
            if (cinemaHall == null) 
            {
                return Response.error(DeleteCinemaHallResponse.class, "Could not find cinema for id " + request.getId());
            }
            try 
            {
                for (MovieScreening screening : cinemaHall.getScreenings())
                {
                    for (final BookingState booking : screening.getBookings())
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
                    MovieScreening.delete(screening.getId());
                }
                for (final SeatRow seatRow : cinemaHall.getRows()) 
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
                CinemaHall.delete(cinemaHall.getId());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.OK);
        });
    }
}
