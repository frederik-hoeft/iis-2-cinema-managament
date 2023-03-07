package IIS.Server.api.management.movie;

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

import IIS.Server.api.Response;
import IIS.Server.api.management.movie.requests.*;
import IIS.Server.api.management.movie.responses.*;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Reservation;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;
import generated.cinemaService.proxies.IMovie;
import generated.cinemaService.proxies.MovieProxy;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/movie", produces="application/json")
@CrossOrigin(origins="*")
public class MovieController {
    
    @GetMapping("/list")
    public ResponseEntity<GetMoviesResponse> listMovies() {

        AsyncWorkload<ResponseEntity<GetMoviesResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetMoviesResponse response = new GetMoviesResponse();
            response.setMovies(ObjectX.createFromMany(CinemaService.getInstance().getMovieCache().values(), GetMoviesResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetMoviesResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetMoviesFullResponse> listDetailedMovies() {
        
        AsyncWorkload<ResponseEntity<GetMoviesFullResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetMoviesFullResponse response = new GetMoviesFullResponse();
            List<GetMoviesFullResponseEntry> movies = new ArrayList<GetMoviesFullResponseEntry>();
            for (MovieProxy m : CinemaService.getInstance().getMovieCache().values()) {
                GetMoviesFullResponseEntry entry = ObjectX.createFrom(m, GetMoviesFullResponseEntry.class);
                entry.setScreeningCount(m.getScreenings().size());
                movies.add(entry);
            }
            response.setMovies(movies);
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetMoviesFullResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetMoviesFullResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateMovieResponse> createMovie(@RequestBody CreateMovieRequest request) {

        AsyncWorkload<ResponseEntity<CreateMovieResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            try {
                Movie.createFresh(request.getTitle(), request.getDescription());
            }
            catch (PersistenceException e) {
                CreateMovieResponse response = new CreateMovieResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<CreateMovieResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateMovieResponse response = new CreateMovieResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateMovieResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<CreateMovieResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateMovieResponse> updateMovie(@RequestBody UpdateMovieRequest request) {

        AsyncWorkload<ResponseEntity<UpdateMovieResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getMovieCache().containsKey(request.getId())) {
                UpdateMovieResponse response = new UpdateMovieResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find movie for id " + request.getId()));
                return new ResponseEntity<UpdateMovieResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                Movie movie = CinemaService.getInstance().getMovie(request.getId());
                movie.setTitle(request.getTitle());
                movie.setDescription(request.getDescription());
            }
            catch (PersistenceException e) {
                UpdateMovieResponse response = new UpdateMovieResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateMovieResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateMovieResponse response = new UpdateMovieResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateMovieResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<UpdateMovieResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteMovieResponse> deleteMovie(@RequestBody DeleteMovieRequest request) {

        AsyncWorkload<ResponseEntity<DeleteMovieResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            final var movie = CinemaService.getCacheOf(IMovie.class).getOrDefault(request.getId(), null);
            if (movie == null) 
            {
                return Response.error(DeleteMovieResponse.class, "Could not find movie for id " + request.getId());
            }
            try 
            {
                for (final MovieScreening screening : movie.getScreenings()) 
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
                    for (final SeatRow seatRow : screening.getHall().getRows()) 
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
                    CinemaHall.delete(screening.getHall().getId());
                    MovieScreening.delete(screening.getId());
                }
                Movie.delete(movie.getId());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            DeleteMovieResponse response = new DeleteMovieResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteMovieResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<DeleteMovieResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }
}
