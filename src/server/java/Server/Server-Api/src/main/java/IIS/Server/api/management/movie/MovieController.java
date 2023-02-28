package IIS.Server.api.management.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.movie.requests.*;
import IIS.Server.api.management.movie.responses.*;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;

@RestController
@RequestMapping(path="/management/movie", produces="application/json")
@CrossOrigin(origins="*")
public class MovieController {
    
    @GetMapping("/list")
    public ResponseEntity<GetMoviesResponse> getMovies() 
    {
        AsyncWorkload<ResponseEntity<GetMoviesResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetMoviesResponse response = new GetMoviesResponse();
            response.setSuccess(true);
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetMoviesResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateMovieResponse> createMovie(@RequestBody CreateMovieRequest request) {

        CreateMovieResponse response = new CreateMovieResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateMovieResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateMovieResponse> updateMovie(@RequestBody UpdateMovieRequest request) {

        UpdateMovieResponse response = new UpdateMovieResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateMovieResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteMovieResponse> deleteMovie(@RequestBody DeleteMovieRequest request) {

        DeleteMovieResponse response = new DeleteMovieResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteMovieResponse>(response, HttpStatus.OK);
    }
}
