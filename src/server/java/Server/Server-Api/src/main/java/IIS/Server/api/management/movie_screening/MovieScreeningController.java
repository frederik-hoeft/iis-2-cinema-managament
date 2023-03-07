package IIS.Server.api.management.movie_screening;

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

import com.bestvike.linq.Linq;

import IIS.Server.api.BaseController;
import IIS.Server.api.Response;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponse;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import IIS.Server.api.management.movie_screening.requests.*;
import IIS.Server.api.management.movie_screening.responses.*;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Reservation;
import generated.cinemaService.proxies.IMovieScreening;
import generated.cinemaService.proxies.MovieScreeningProxy;

@RestController
@RequestMapping(path="/management/movie-screening", produces="application/json")
@CrossOrigin(origins="*")
public class MovieScreeningController extends BaseController {
    
    @GetMapping("/list")
    public ResponseEntity<GetScreeningsResponse> listMovieScreenings() 
    {
        return scheduled(() -> 
        {
            final var screenings = Linq.of(CinemaService.getSetOf(IMovieScreening.class))
                .select(s -> 
                {
                    final var screening = ObjectX.createFrom(s, GetMovieScreeningsResponseEntry.class);
                    screening.setMovieTitle(s.getMovie().getTitle());
                    return screening;
                })
                .toList();
            GetScreeningsResponse response = new GetScreeningsResponse();
            response.setScreenings(screenings);
            response.setSuccess(true);
            return new ResponseEntity<GetScreeningsResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetMovieScreeningsFullResponse> listDetailedMovieScreenings() 
    {
        return scheduled(() -> 
        {
            GetMovieScreeningsFullResponse response = new GetMovieScreeningsFullResponse();
            List<GetMovieScreeningsFullResponseEntry> screenings = new ArrayList<GetMovieScreeningsFullResponseEntry>();
            for (MovieScreeningProxy ms : CinemaService.getInstance().getMovieScreeningCache().values()) {
                GetMovieScreeningsFullResponseEntry entry = ObjectX.createFrom(ms, GetMovieScreeningsFullResponseEntry.class);
                entry.setMovie(ObjectX.createFrom(ms.getMovie(), GetMoviesResponseEntry.class));
                entry.setHall(ObjectX.createFrom(ms.getHall(), GetCinemaHallsResponseEntry.class));
                screenings.add(entry);
            }
            response.setScreenings(screenings);
            response.setSuccess(true);
            return new ResponseEntity<GetMovieScreeningsFullResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/create")
    public ResponseEntity<CreateScreeningResponse> createMovieScreening(@RequestBody CreateScreeningRequest request) 
    {
        return scheduled(() -> 
        {
            List<String> errors = new ArrayList<String>();
            if (!CinemaService.getInstance().getMovieCache().containsKey(request.getMovieId())) {
                errors.add("Could not find movie for id " + request.getMovieId() + ".\n");
            }
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                errors.add("Could not find cinema hall for id " + request.getCinemaHallId() + ".\n");
            }

            if (errors.size() > 0) {
                String error = "";
                for (String string : errors) {
                    error += string;
                }

                CreateScreeningResponse response = new CreateScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of(error));
                return new ResponseEntity<CreateScreeningResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try 
            {
                Movie movie = CinemaService.getInstance().getMovie(request.getMovieId());
                CinemaHall hall = CinemaService.getInstance().getCinemaHall(request.getCinemaHallId());
                MovieScreening.createFresh(request.getHasExpired(), request.getName(), movie, hall);
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            CreateScreeningResponse response = new CreateScreeningResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateScreeningResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateScreeningResponse> updateMovieScreening(@RequestBody UpdateScreeningRequest request) 
    {
        return scheduled(() -> 
        {
            if (!CinemaService.getInstance().getMovieScreeningCache().containsKey(request.getId())) 
            {
                UpdateScreeningResponse response = new UpdateScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find movie screening for id " + request.getId()));
                return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.BAD_REQUEST);
            }
            try 
            {
                MovieScreening screening = CinemaService.getInstance().getMovieScreening(request.getId());
                screening.setFinished(request.getNewFinished());
                screening.setName(request.getNewName());
            }
            catch (Exception e) 
            {
                return Response.error(e);
            }

            UpdateScreeningResponse response = new UpdateScreeningResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteScreeningResponse> deleteMovieScreening(@RequestBody DeleteScreeningRequest request) 
    {
        return scheduled(() -> 
        {
            final var screening = CinemaService.getCacheOf(IMovieScreening.class).getOrDefault(request.getId(), null);
            if (screening == null) 
            {
                return Response.error(DeleteScreeningResponse.class, "Could not find seat row for id " + request.getId());
            }
            try 
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
            catch (Exception e) 
            {
                return Response.error(e);
            }

            DeleteScreeningResponse response = new DeleteScreeningResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/available-movies")
    public ResponseEntity<GetMoviesResponse> getAvailableMovies() 
    {
        return scheduled(() -> 
        {
            GetMoviesResponse response = new GetMoviesResponse();
            response.setMovies(ObjectX.createFromMany(CinemaService.getInstance().getMovieCache().values(), GetMoviesResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/available-halls")
    public ResponseEntity<GetCinemaHallsResponse> getAvailableHalls() 
    {
        return scheduled(() -> 
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values().stream()
                .filter(c -> c.getAvailable())
                .toList(), GetCinemaHallsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
    }
}
