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

import com.bestvike.linq.Linq;

import IIS.Server.api.BaseController;
import IIS.Server.api.PriceCategoryEnum;
import IIS.Server.api.Response;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponse;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponseEntry;
import IIS.Server.api.management.seat.responses.GetSeatsFullResponseEntry;
import IIS.Server.api.management.seat.responses.GetSeatsResponse;
import IIS.Server.api.management.seat.responses.GetSeatsResponseEntry;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.api.user.account.responses.GetUserAccountsResponseEntry;
import IIS.Server.api.user.booking.requests.*;
import IIS.Server.api.user.booking.responses.*;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.Reservation;
import generated.cinemaService.proxies.IBooking;
import generated.cinemaService.proxies.ICustomer;
import generated.cinemaService.proxies.IMovieScreening;
import generated.cinemaService.proxies.IReservation;
import generated.cinemaService.proxies.ISeat;

@RestController
@RequestMapping(path="/user/booking", produces="application/json")
@CrossOrigin(origins="*")
public class BookingController extends BaseController 
{
    @PostMapping("/book")
    public ResponseEntity<UserBookingResponse> book(@RequestBody UserBookingRequest request) 
    {
        return isolated(() -> 
        {
            CinemaService.getInstance().getCustomerCache();
            final ICustomer user = Linq.of(CinemaService.getSetOf(ICustomer.class)).firstOrDefault(c -> c.getEmail().equalsIgnoreCase(request.getEmail()));
            if (user == null) 
            {
                return Response.error(UserBookingResponse.class, "user does not exist!");
            }
            final ISeat seat = CinemaService.getCacheOf(ISeat.class).getOrDefault(request.getSeatId(), null);
            if (seat == null) 
            {
                return Response.error(UserBookingResponse.class, "seat does not exist!");
            }
            final IMovieScreening screening = CinemaService.getCacheOf(IMovieScreening.class).getOrDefault(request.getScreeningId(), null);
            if (screening == null) 
            {
                return Response.error(UserBookingResponse.class, "movie screening does not exist!");
            }
            if (Linq.of(screening.getBookings()).any(b -> b.getSeat().getId() == seat.getId()))
            {
                return Response.error(UserBookingResponse.class, "the specified seat is already occupied!");
            }
            try 
            {
                Booking.createFresh(screening.getTheObject(), seat.getTheObject(), user.getTheObject());
            } 
            catch (Exception e) 
            {
                return Response.error(e);
            }
            UserBookingResponse response = new UserBookingResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserBookingResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/reserve")
    public ResponseEntity<UserReservationResponse> reserve(@RequestBody UserReservationRequest request) 
    {
        return isolated(() -> 
        {
            final ICustomer user = Linq.of(CinemaService.getSetOf(ICustomer.class)).firstOrDefault(c -> c.getEmail().equalsIgnoreCase(request.getEmail()));
            if (user == null) 
            {
                return Response.error(UserReservationResponse.class, "user does not exist!");
            }
            final ISeat seat = CinemaService.getCacheOf(ISeat.class).getOrDefault(request.getSeatId(), null);
            if (seat == null) 
            {
                return Response.error(UserReservationResponse.class, "seat does not exist!");
            }
            final IMovieScreening screening = CinemaService.getCacheOf(IMovieScreening.class).getOrDefault(request.getScreeningId(), null);
            if (screening == null) 
            {
                return Response.error(UserReservationResponse.class, "movie screening does not exist!");
            }
            if (Linq.of(screening.getBookings()).any(b -> b.getSeat().getId() == seat.getId()))
            {
                return Response.error(UserReservationResponse.class, "the specified seat is already occupied!");
            }
            try 
            {
                Reservation.createFresh(screening.getTheObject(), seat.getTheObject(), user.getTheObject());
            } 
            catch (Exception e) 
            {
                return Response.error(e);
            }
            UserReservationResponse response = new UserReservationResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserReservationResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/upgrade")
    public ResponseEntity<UserUpgradeReservationResponse> upgradeReservation(@RequestBody UserUpgradeReservationRequest request) 
    {
        return isolated(() -> 
        {
            final var reservation = CinemaService.getCacheOf(IReservation.class).getOrDefault(request.getReservationId(), null);
            if (reservation == null || !reservation.getCustomer().getEmail().equalsIgnoreCase(request.getEmail()))
            {
                return Response.error(UserUpgradeReservationResponse.class, "No such reservation for " + request.getEmail());
            }
            try 
            {
                final var screening = reservation.getScreening();
                final var seat = reservation.getSeat();
                final var user = reservation.getCustomer();
                Reservation.delete(reservation.getId());
                Booking.createFresh(screening, seat, user);
            } catch (Exception e) {
                return Response.error(UserUpgradeReservationResponse.class, e.toString());
            }
            UserUpgradeReservationResponse response = new UserUpgradeReservationResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserUpgradeReservationResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/cancel")
    public ResponseEntity<UserCancelReservationResponse> cancelReservation(@RequestBody UserCancelReservationRequest request) 
    {
        return isolated(() -> 
        {
            final var reservation = CinemaService.getCacheOf(IReservation.class).getOrDefault(request.getReservationId(), null);
            if (reservation == null || !reservation.getCustomer().getEmail().equalsIgnoreCase(request.getEmail()))
            {
                return Response.error(UserCancelReservationResponse.class, "No such reservation for " + request.getEmail());
            }
            try 
            {
                Reservation.delete(reservation.getId());
            } catch (Exception e) {
                return Response.error(UserCancelReservationResponse.class, e.toString());
            }
            UserCancelReservationResponse response = new UserCancelReservationResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserCancelReservationResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/get-reservations")
    public ResponseEntity<GetUserReservationsResponse> getReservations(@RequestBody GetUserReservationsRequest request) 
    {
        return isolated(() -> 
        {
            final var reservations = CinemaService.getSetOf(IReservation.class).stream()
                .filter(b -> b.getCustomer().getEmail().equalsIgnoreCase(request.getEmail()))
                .map(b -> 
                {
                    final var result = ObjectX.createFrom(b, GetUserReservationsResponseEntry.class);
                    final var screeningProxy = b.getScreening();
                    final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                    screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                    screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                    result.setMovieScreening(screening);
                    final var s = b.getSeat();
                    final var r = s.getRow();
                    final var seat = ObjectX.createFrom(s, GetSeatsFullResponseEntry.class);
                    final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                    row.setSeatCount(r.getSeats().size());
                    row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                    seat.setRow(row);
                    result.setSeat(seat);
                    result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                    return result;
                })
                .collect(Collectors.toList());
            GetUserReservationsResponse response = new GetUserReservationsResponse();
            response.setSuccess(true);
            response.setReservations(reservations);
            return new ResponseEntity<GetUserReservationsResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-reservations")
    public ResponseEntity<GetUserReservationsResponse> listReservations() 
    {
        return isolated(() -> 
        {
            final var reservations = CinemaService.getSetOf(IReservation.class).stream()
                .map(b -> 
                {
                    final var result = ObjectX.createFrom(b, GetUserReservationsResponseEntry.class);
                    final var screeningProxy = b.getScreening();
                    final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                    screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                    screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                    result.setMovieScreening(screening);
                    final var s = b.getSeat();
                    final var r = s.getRow();
                    final var seat = ObjectX.createFrom(s, GetSeatsFullResponseEntry.class);
                    final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                    row.setSeatCount(r.getSeats().size());
                    row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                    seat.setRow(row);
                    result.setSeat(seat);
                    result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                    return result;
                })
                .collect(Collectors.toList());
            GetUserReservationsResponse response = new GetUserReservationsResponse();
            response.setSuccess(true);
            response.setReservations(reservations);
            return new ResponseEntity<GetUserReservationsResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/get-bookings")
    public ResponseEntity<GetUserBookingsResponse> getBookings(@RequestBody GetUserBookingsRequest request) 
    {
        return isolated(() -> 
        {
            final var bookings = CinemaService.getSetOf(IBooking.class).stream()
                .filter(b -> b.getCustomer().getEmail().equalsIgnoreCase(request.getEmail()))
                .map(b -> 
                {
                    final var result = ObjectX.createFrom(b, GetUserBookingsResponseEntry.class);
                    final var screeningProxy = b.getScreening();
                    final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                    screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                    screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                    result.setMovieScreening(screening);
                    final var s = b.getSeat();
                    final var r = s.getRow();
                    final var seat = ObjectX.createFrom(s, GetSeatsFullResponseEntry.class);
                    final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                    row.setSeatCount(r.getSeats().size());
                    row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                    seat.setRow(row);
                    result.setSeat(seat);
                    result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                    return result;
                })
                .collect(Collectors.toList());
            GetUserBookingsResponse response = new GetUserBookingsResponse();
            response.setSuccess(true);
            response.setBookings(bookings);
            return new ResponseEntity<GetUserBookingsResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-bookings")
    public ResponseEntity<GetUserBookingsResponse> listBookings() 
    {
        return isolated(() -> 
        {
            final var bookings = CinemaService.getSetOf(IBooking.class).stream()
                .map(b -> 
                {
                    final var result = ObjectX.createFrom(b, GetUserBookingsResponseEntry.class);
                    final var screeningProxy = b.getScreening();
                    final var screening = ObjectX.createFrom(screeningProxy, GetMovieScreeningsFullResponseEntry.class);
                    screening.setMovie(ObjectX.createFrom(screeningProxy.getMovie(), GetMoviesResponseEntry.class));
                    screening.setHall(ObjectX.createFrom(screeningProxy.getHall(), GetCinemaHallsResponseEntry.class));
                    result.setMovieScreening(screening);
                    final var s = b.getSeat();
                    final var r = s.getRow();
                    final var seat = ObjectX.createFrom(s, GetSeatsFullResponseEntry.class);
                    final var row = ObjectX.createFrom(r, GetSeatRowsResponseEntry.class);
                    row.setSeatCount(r.getSeats().size());
                    row.setPrice(PriceCategoryEnum.from(r.getPrice()));
                    seat.setRow(row);
                    result.setSeat(seat);
                    result.setUser(ObjectX.createFrom(b.getCustomer(), GetUserAccountsResponseEntry.class));
                    return result;
                })
                .collect(Collectors.toList());
            GetUserBookingsResponse response = new GetUserBookingsResponse();
            response.setSuccess(true);
            response.setBookings(bookings);
            return new ResponseEntity<GetUserBookingsResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/available-seats")
    public ResponseEntity<GetSeatsResponse> getAvailableSeats(@RequestBody GetAvailableSeatsRequest request) 
    {
        return isolated(() -> 
        {
            final var screening = CinemaService.getInstance().getMovieScreening(request.getScreeningId());
            final var seats = CinemaService.getSetOf(ISeat.class).stream()
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
        });
    }

    @PostMapping("/available-seat-rows")
    public ResponseEntity<GetAvailableSeatRowsResponse> getAvailableSeatRows(@RequestBody GetAvailableSeatRowsRequest request) 
    {
        return isolated(() -> 
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
                    result.setPriceCategory(PriceCategoryEnum.from(r.getPrice()));
                    return result;
                })
                .collect(Collectors.toList());
            GetAvailableSeatRowsResponse response = new GetAvailableSeatRowsResponse();
            response.setRows(rows);
            response.setSuccess(true);
            return new ResponseEntity<GetAvailableSeatRowsResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/available-screenings")
    public ResponseEntity<GetMovieScreeningsFullResponse> getAvailableScreenings(@RequestBody GetAvailableScreeningsRequest request) 
    {
        return isolated(() -> 
        {
            final List<GetMovieScreeningsFullResponseEntry> screenings = CinemaService.getSetOf(IMovieScreening.class).stream()
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
            response.setSuccess(true);
            response.setScreenings(screenings);
            return new ResponseEntity<GetMovieScreeningsFullResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/available-movies")
    public ResponseEntity<GetMoviesResponse> getAvailableMovies() 
    {
        return isolated(() ->
        {
            final List<Movie> movieProxies = CinemaService.getSetOf(IMovieScreening.class).stream()
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
