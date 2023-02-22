package IIS.Server.api.management.cinema_hall;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.cinema_hall.requests.*;
import IIS.Server.api.management.cinema_hall.responses.*;

@RestController
@RequestMapping(path="/management/cinema-hall", produces="application/json")
@CrossOrigin(origins="*")
public class CinemaHallController {
    
    @GetMapping("/list")
    public ResponseEntity<GetCinemaHallsResponse> getCinemaHalls() {

        GetCinemaHallsResponse response = new GetCinemaHallsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateCinemaHallResponse> createMovie(@RequestBody CreateCinemaHallRequest request) {

        CreateCinemaHallResponse response = new CreateCinemaHallResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateCinemaHallResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateCinemaHallResponse> updateMovie(@RequestBody UpdateCinemaHallRequest request) {

        UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteCinemaHallResponse> deleteMovie(@RequestBody DeleteCinemaHallRequest request) {

        DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.OK);
    }
}
