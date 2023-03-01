package IIS.Server.api.management.movie_screening;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie_screening.requests.*;
import IIS.Server.api.management.movie_screening.responses.*;

@RestController
@RequestMapping(path="/management/movie-screening", produces="application/json")
@CrossOrigin(origins="*")
public class MovieScreeningController {
    
    @GetMapping("/list")
    public ResponseEntity<GetScreeningsResponse> listMovieScreenings() {

        GetScreeningsResponse response = new GetScreeningsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetScreeningsResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetMovieScreeningsFullResponse> listDetailedMovieScreenings() {

        GetMovieScreeningsFullResponse response = new GetMovieScreeningsFullResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetMovieScreeningsFullResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateScreeningResponse> createMovieScreening(@RequestBody CreateScreeningRequest request) {

        CreateScreeningResponse response = new CreateScreeningResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateScreeningResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateScreeningResponse> updateMovieScreening(@RequestBody UpdateScreeningRequest request) {

        UpdateScreeningResponse response = new UpdateScreeningResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteScreeningResponse> deleteMovieScreening(@RequestBody DeleteScreeningRequest request) {

        DeleteScreeningResponse response = new DeleteScreeningResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/available-movies")
    public ResponseEntity<GetMoviesResponse> getAvailableMovies() {

        GetMoviesResponse response = new GetMoviesResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/available-halls")
    public ResponseEntity<GetCinemaHallsResponse> getAvailableHalls() {

        GetCinemaHallsResponse response = new GetCinemaHallsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
    }
}
