package IIS.Server.api.management.movie_screening;

import java.lang.StackWalker.Option;
import java.sql.SQLException;
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

import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponse;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import IIS.Server.api.management.movie_screening.requests.*;
import IIS.Server.api.management.movie_screening.responses.*;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import exceptions.ConstraintViolation;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.proxies.MovieScreeningProxy;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/movie-screening", produces="application/json")
@CrossOrigin(origins="*")
public class MovieScreeningController {
    
    @GetMapping("/list")
    public ResponseEntity<GetScreeningsResponse> listMovieScreenings() {

        AsyncWorkload<ResponseEntity<GetScreeningsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetScreeningsResponse response = new GetScreeningsResponse();
            response.setScreenings(ObjectX.createFromMany(CinemaService.getInstance().getMovieScreeningCache().values(), GetMovieScreeningsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetScreeningsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetScreeningsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetMovieScreeningsFullResponse> listDetailedMovieScreenings() {

        AsyncWorkload<ResponseEntity<GetMovieScreeningsFullResponse>> workload = PersistencyService.getInstance().schedule(() -> 
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
            response.setError(Optional.empty());
            return new ResponseEntity<GetMovieScreeningsFullResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetMovieScreeningsFullResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateScreeningResponse> createMovieScreening(@RequestBody CreateScreeningRequest request) {

        AsyncWorkload<ResponseEntity<CreateScreeningResponse>> workload = PersistencyService.getInstance().schedule(() -> 
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

            try {
                Movie movie = CinemaService.getInstance().getMovie(request.getMovieId());
                CinemaHall hall = CinemaService.getInstance().getCinemaHall(request.getCinemaHallId());
                MovieScreening.createFresh(movie, hall, request.getHasExpired(), request.getName());
            }
            catch (PersistenceException e) {
                CreateScreeningResponse response = new CreateScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<CreateScreeningResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateScreeningResponse response = new CreateScreeningResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateScreeningResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<CreateScreeningResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateScreeningResponse> updateMovieScreening(@RequestBody UpdateScreeningRequest request) {

        AsyncWorkload<ResponseEntity<UpdateScreeningResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getMovieScreeningCache().containsKey(request.getId())) {
                UpdateScreeningResponse response = new UpdateScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find movie screening for id " + request.getId()));
                return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                MovieScreening screening = CinemaService.getInstance().getMovieScreening(request.getId());
                screening.setFinished(request.getNewFinished());
                screening.setName(request.getNewName());
            }
            catch (PersistenceException e) {
                UpdateScreeningResponse response = new UpdateScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateScreeningResponse response = new UpdateScreeningResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<UpdateScreeningResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteScreeningResponse> deleteMovieScreening(@RequestBody DeleteScreeningRequest request) {

        AsyncWorkload<ResponseEntity<DeleteScreeningResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getMovieScreeningCache().containsKey(request.getId())) {
                DeleteScreeningResponse response = new DeleteScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find movie screening for id " + request.getId()));
                return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                MovieScreening.delete(request.getId());
            }
            catch (ConstraintViolation e) {
                DeleteScreeningResponse response = new DeleteScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (SQLException e) {
                DeleteScreeningResponse response = new DeleteScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (NoConnectionException e) {
                DeleteScreeningResponse response = new DeleteScreeningResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            DeleteScreeningResponse response = new DeleteScreeningResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<DeleteScreeningResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @GetMapping("/available-movies")
    public ResponseEntity<GetMoviesResponse> getAvailableMovies() {

        AsyncWorkload<ResponseEntity<GetMoviesResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetMoviesResponse response = new GetMoviesResponse();
            response.setMovies(ObjectX.createFromMany(CinemaService.getInstance().getMovieCache().values(), GetMoviesResponseEntry.class));
            response.setSuccess(false);
            response.setError(Optional.empty());
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetMoviesResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @GetMapping("/available-halls")
    public ResponseEntity<GetCinemaHallsResponse> getAvailableHalls() {

        AsyncWorkload<ResponseEntity<GetCinemaHallsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values().stream().filter(c -> c.getAvailable()).toList(), GetCinemaHallsResponseEntry.class));
            response.setSuccess(false);
            response.setError(Optional.empty());
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetCinemaHallsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }
}
