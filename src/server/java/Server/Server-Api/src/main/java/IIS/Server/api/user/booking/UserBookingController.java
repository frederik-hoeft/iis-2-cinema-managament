package IIS.Server.api.user.booking;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponse;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponseEntry;
import IIS.Server.api.management.seat.requests.GetSeatsRequest;
import IIS.Server.api.management.seat.responses.GetSeatsResponse;
import IIS.Server.api.management.seat_row.requests.GetSeatRowRequest;
import IIS.Server.api.user.booking.requests.*;
import IIS.Server.api.user.booking.responses.*;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;


@RestController
@RequestMapping(path="/user/booking", produces="application/json")
@CrossOrigin(origins="*")
public class UserBookingController {
    
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
    public ResponseEntity<UserCancelReservationResponse> cancelReservation(@RequestBody UserCancelReservationRequest request) {

        UserCancelReservationResponse response = new UserCancelReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserCancelReservationResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/get-reservations")
    public ResponseEntity<GetUserReservationsResponse> getReservations(@RequestBody GetUserReservationsRequest request) {

        GetUserReservationsResponse response = new GetUserReservationsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetUserReservationsResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/list-reservations")
    public ResponseEntity<GetUserReservationsResponse> listReservations() {

        GetUserReservationsResponse response = new GetUserReservationsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetUserReservationsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/get-bookings")
    public ResponseEntity<GetUserBookingsResponse> getBookings(@RequestBody GetUserBookingsRequest request) {

        GetUserBookingsResponse response = new GetUserBookingsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetUserBookingsResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/list-bookings")
    public ResponseEntity<GetUserBookingsResponse> listBookings() {

        GetUserBookingsResponse response = new GetUserBookingsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetUserBookingsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/available-seats")
    public ResponseEntity<GetSeatsResponse> getAvailableSeats(@RequestBody GetSeatsRequest request) {

        GetSeatsResponse response = new GetSeatsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/available-seat-rows")
    public ResponseEntity<GetAvailableSeatRowsResponse> getAvailableSeatRows(@RequestBody GetSeatRowRequest request) 
    {
        GetAvailableSeatRowsResponse response = new GetAvailableSeatRowsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetAvailableSeatRowsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/available-screenings")
    public ResponseEntity<GetMovieScreeningsFullResponse> getAvailableScreenings(@RequestBody GetAvailableScreeningsRequest request) 
    {
        final List<GetMovieScreeningsFullResponseEntry> screenings = CinemaService.getSetOf(MovieScreening.class).stream()
            .filter(s -> !s.getFinished() && s.getMovie().getId() == request.getMovieId())
            .map(s -> {
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
        final List<Movie> movieProxies = CinemaService.getSetOf(MovieScreening.class).stream()
            .filter(screening -> !screening.getFinished())
            .map(screening -> screening.getMovie())
            .collect(Collectors.toList());
        final Collection<GetMoviesResponseEntry> movies = ObjectX.createFromMany(movieProxies, GetMoviesResponseEntry.class);
        GetMoviesResponse response = new GetMoviesResponse();
        response.setSuccess(true);
        response.setMovies(movies);
        return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
    }
}
