package IIS.Server.api.management.movie_screening;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.movie_screening.requests.*;
import IIS.Server.api.management.movie_screening.responses.*;

@RestController
@RequestMapping(path="/management/movie-screening", produces="application/json")
@CrossOrigin(origins="*")
public class MovieScreeningController {
    
    @GetMapping("/list")
    public ResponseEntity<GetScreeningsResponse> getMovieScreenings() {

        GetScreeningsResponse response = new GetScreeningsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetScreeningsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateScreeningResponse> createMovie(@RequestBody CreateScreeningRequest request) {

        CreateScreeningResponse response = new CreateScreeningResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateScreeningResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateScreeningResponse> updateMovie(@RequestBody UpdateScreeningRequest request) {

        UpdateScreeningResponse response = new UpdateScreeningResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateScreeningResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteScreeningResponse> deleteMovie(@RequestBody DeleteScreeningRequest request) {

        DeleteScreeningResponse response = new DeleteScreeningResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteScreeningResponse>(response, HttpStatus.OK);
    }
}
