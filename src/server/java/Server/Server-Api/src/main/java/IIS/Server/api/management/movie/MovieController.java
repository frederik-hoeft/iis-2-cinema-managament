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

import IIS.Server.api.BaseController;
import IIS.Server.api.Response;
import IIS.Server.api.management.movie.requests.*;
import IIS.Server.api.management.movie.responses.*;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Reservation;
import generated.cinemaService.proxies.IMovie;
import generated.cinemaService.proxies.MovieProxy;

@RestController
@RequestMapping(path="/management/movie", produces="application/json")
@CrossOrigin(origins="*")
public class MovieController extends BaseController
{
    @GetMapping("/list")
    public ResponseEntity<GetMoviesResponse> listMovies() 
    {
        return scheduled(() -> 
        {
            GetMoviesResponse response = new GetMoviesResponse();
            response.setMovies(ObjectX.createFromMany(CinemaService.getInstance().getMovieCache().values(), GetMoviesResponseEntry.class));
            response.setSuccess(true);
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetMoviesFullResponse> listDetailedMovies() 
    {
        return scheduled(() -> 
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
            return new ResponseEntity<GetMoviesFullResponse>(response, HttpStatus.OK);
        });
    }
    
    @PostMapping("/create")
    public ResponseEntity<CreateMovieResponse> createMovie(@RequestBody CreateMovieRequest request) 
    {
        return scheduled(() -> 
        {
            try 
            {
                Movie.createFresh(request.getTitle(), request.getDescription());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }
            CreateMovieResponse response = new CreateMovieResponse();
            response.setSuccess(true);
            return new ResponseEntity<CreateMovieResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateMovieResponse> updateMovie(@RequestBody UpdateMovieRequest request) 
    {
        return scheduled(() -> 
        {
            final var movie = CinemaService.getCacheOf(IMovie.class).getOrDefault(request.getId(), null);
            if (movie == null) 
            {
                return Response.error(UpdateMovieResponse.class, "Could not find movie for id " + request.getId());
            }
            try 
            {
                movie.setTitle(request.getTitle());
                movie.setDescription(request.getDescription());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            UpdateMovieResponse response = new UpdateMovieResponse();
            response.setSuccess(true);
            return new ResponseEntity<UpdateMovieResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteMovieResponse> deleteMovie(@RequestBody DeleteMovieRequest request) 
    {
        return scheduled(() -> 
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
            return new ResponseEntity<DeleteMovieResponse>(response, HttpStatus.OK);
        });
    }
}
