package IIS.Server.api.user.booking;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.Result;

import IIS.Server.api.BaseController;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponse;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponseEntry;
import IIS.Server.api.management.seat.requests.GetSeatsRequest;
import IIS.Server.api.management.seat.responses.GetSeatsFullResponseEntry;
import IIS.Server.api.management.seat.responses.GetSeatsResponse;
import IIS.Server.api.management.seat.responses.GetSeatsResponseEntry;
import IIS.Server.api.management.seat_row.requests.GetSeatRowRequest;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.api.user.account.responses.GetUserAccountsResponseEntry;
import IIS.Server.api.user.booking.requests.*;
import IIS.Server.api.user.booking.responses.*;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;


@RestController
@RequestMapping(path="/user/booking", produces="application/json")
@CrossOrigin(origins="*")
public class UserBookingController extends BaseController {
    
    @PostMapping("/book")
    public ResponseEntity<UserBookingResponse> book(@RequestBody UserBookingRequest request) {

        UserBookingResponse response = new UserBookingResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserBookingResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<UserReservationResponse> reserve(@RequestBody UserReservationRequest request) {

        UserReservationResponse response = new UserReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserReservationResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/upgrade")
    public ResponseEntity<UserUpgradeReservationResponse> upgradeReservation(@RequestBody UserUpgradeReservationRequest request) {

        UserUpgradeReservationResponse response = new UserUpgradeReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserUpgradeReservationResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<UserCancelReservationResponse> cancelReservation(@RequestBody UserCancelReservationRequest request) 
    {

        UserCancelReservationResponse response = new UserCancelReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserCancelReservationResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/get-reservations")
    public ResponseEntity<GetUserReservationsResponse> getReservations(@RequestBody GetUserReservationsRequest request) 
    {
        final var reservations = CinemaService.getSetOf(Booking.class).stream()
            .filter(b -> b.getCustomer().getEmail().equals(request.getEmail()))
            .map(b -> 
            {
                final var result = ObjectX.createFrom(b, GetUserReservationsResponseEntry.class);
                final var screeningProxy = b.getScreening();
                final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                result.setMovieScreening(screening);
                final var seatProxy = b.getSeat();
                final var seat = ObjectX.createFrom(seatProxy, GetSeatsFullResponseEntry.class);
                seat.setRow(ObjectX.createFrom(seatProxy, GetSeatRowsResponseEntry.class));
                result.setSeat(seat);
                result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                return result;
            })
            .collect(Collectors.toList());
        GetUserReservationsResponse response = new GetUserReservationsResponse();
        response.setSuccess(true);
        response.setReservations(reservations);
        return new ResponseEntity<GetUserReservationsResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/list-reservations")
    public ResponseEntity<GetUserReservationsResponse> listReservations() 
    {
        final var reservations = CinemaService.getSetOf(Booking.class).stream()
            .map(b -> 
            {
                final var result = ObjectX.createFrom(b, GetUserReservationsResponseEntry.class);
                final var screeningProxy = b.getScreening();
                final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                result.setMovieScreening(screening);
                final var seatProxy = b.getSeat();
                final var seat = ObjectX.createFrom(seatProxy, GetSeatsFullResponseEntry.class);
                seat.setRow(ObjectX.createFrom(seatProxy, GetSeatRowsResponseEntry.class));
                result.setSeat(seat);
                result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                return result;
            })
            .collect(Collectors.toList());
        GetUserReservationsResponse response = new GetUserReservationsResponse();
        response.setSuccess(true);
        response.setReservations(reservations);
        return new ResponseEntity<GetUserReservationsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/get-bookings")
    public ResponseEntity<GetUserBookingsResponse> getBookings(@RequestBody GetUserBookingsRequest request) 
    {
        final var bookings = CinemaService.getSetOf(Booking.class).stream()
            .filter(b -> b.getCustomer().getEmail().equals(request.getEmail()))
            .map(b -> 
            {
                final var result = ObjectX.createFrom(b, GetUserBookingsResponseEntry.class);
                final var screeningProxy = b.getScreening();
                final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                result.setMovieScreening(screening);
                final var seatProxy = b.getSeat();
                final var seat = ObjectX.createFrom(seatProxy, GetSeatsFullResponseEntry.class);
                seat.setRow(ObjectX.createFrom(seatProxy, GetSeatRowsResponseEntry.class));
                result.setSeat(seat);
                result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                return result;
            })
            .collect(Collectors.toList());
        GetUserBookingsResponse response = new GetUserBookingsResponse();
        response.setSuccess(true);
        response.setBookings(bookings);
        return new ResponseEntity<GetUserBookingsResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/list-bookings")
    public ResponseEntity<GetUserBookingsResponse> listBookings() 
    {
        final var bookings = CinemaService.getSetOf(Booking.class).stream()
            .map(b -> 
            {
                final var result = ObjectX.createFrom(b, GetUserBookingsResponseEntry.class);
                final var screeningProxy = b.getScreening();
                final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                result.setMovieScreening(screening);
                final var seatProxy = b.getSeat();
                final var seat = ObjectX.createFrom(seatProxy, GetSeatsFullResponseEntry.class);
                seat.setRow(ObjectX.createFrom(seatProxy, GetSeatRowsResponseEntry.class));
                result.setSeat(seat);
                result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                return result;
            })
            .collect(Collectors.toList());
        GetUserBookingsResponse response = new GetUserBookingsResponse();
        response.setSuccess(true);
        response.setBookings(bookings);
        return new ResponseEntity<GetUserBookingsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/available-seats")
    public ResponseEntity<GetSeatsResponse> getAvailableSeats(@RequestBody GetAvailableSeatsRequest request) 
    {
        final var screening = CinemaService.getInstance().getMovieScreening(request.getScreeningId());
        final var seats = CinemaService.getSetOf(Seat.class).stream()
            // matching rows && any free seat
            .filter(s -> s.getRow().getId() == request.getRowId() 
                && !screening.getBookings().stream()
                    .anyMatch(b -> b.getSeat().getId() == s.getId()))
            .map(s -> ObjectX.createFrom(s, GetSeatsResponseEntry.class))
            .collect(Collectors.toList());
        GetSeatsResponse response = new GetSeatsResponse();
        response.setSeats(seats);
        response.setSuccess(true);
        return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/available-seat-rows")
    public ResponseEntity<GetAvailableSeatRowsResponse> getAvailableSeatRows(@RequestBody GetAvailableSeatRowsRequest request) 
    {
        final var screening = CinemaService.getInstance().getMovieScreening(request.getScreeningId());
        final var rows = screening.getHall().getRows().stream()
            // filter by any free seat
            .filter(r -> r.getSeats().stream().anyMatch(s -> !screening.getBookings().stream()
                .anyMatch(b -> b.getSeat().getId() == s.getId())))
            .map(r -> 
            {
                final var result = new GetAvailableSeatRowsResponseEntry();
                result.setAvailableSeatCount(r.getSeats().size());
                result.setName(r.getName());
                result.setRowId(r.getId());
                result.setPriceCategory(r.getPrice());
                return result;
            })
            .collect(Collectors.toList());
        GetAvailableSeatRowsResponse response = new GetAvailableSeatRowsResponse();
        response.setRows(rows);
        response.setSuccess(true);
        return new ResponseEntity<GetAvailableSeatRowsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/available-screenings")
    public ResponseEntity<GetMovieScreeningsFullResponse> getAvailableScreenings(@RequestBody GetAvailableScreeningsRequest request) 
    {
        final List<GetMovieScreeningsFullResponseEntry> screenings = CinemaService.getSetOf(MovieScreening.class).stream()
            .filter(s -> !s.getFinished() && s.getMovie().getId() == request.getMovieId())
            .map(s -> 
            {
                final GetMovieScreeningsFullResponseEntry result = new GetMovieScreeningsFullResponseEntry();
                result.setFinished(s.getFinished());
                result.setMovie(ObjectX.createFrom(s.getMovie(), GetMoviesResponseEntry.class));
                result.setHall(ObjectX.createFrom(s.getHall(), GetCinemaHallsResponseEntry.class));
                result.setId(s.getId());
                result.setName(s.getName());
                return result;
            })
            .collect(Collectors.toList());
        GetMovieScreeningsFullResponse response = new GetMovieScreeningsFullResponse();
        response.setSuccess(false);
        response.setScreenings(screenings);
        return new ResponseEntity<GetMovieScreeningsFullResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/available-movies")
    public ResponseEntity<GetMoviesResponse> getAvailableMovies() 
    {
        return scheduled(() ->
        {
            final List<Movie> movieProxies = CinemaService.getSetOf(MovieScreening.class).stream()
                .filter(screening -> !screening.getFinished())
                .map(screening -> screening.getMovie())
                .collect(Collectors.toList());
            final Collection<GetMoviesResponseEntry> movies = ObjectX.createFromMany(movieProxies, GetMoviesResponseEntry.class);
            GetMoviesResponse response = new GetMoviesResponse();
            response.setSuccess(true);
            response.setMovies(movies);
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
    }
}
