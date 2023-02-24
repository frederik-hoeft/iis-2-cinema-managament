package IIS.Server.api.management.seat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.seat.requests.*;
import IIS.Server.api.management.seat.responses.*;

@RestController
@RequestMapping(path="/management/seat", produces="application/json")
@CrossOrigin(origins="*")
public class SeatController {
    
    @GetMapping("/list")
    public ResponseEntity<GetSeatsResponse> getSeats() {

        GetSeatsResponse response = new GetSeatsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSeatResponse> createSeat(@RequestBody CreateSeatRequest request) {

        CreateSeatResponse response = new CreateSeatResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateSeatResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatResponse> updateSeat(@RequestBody UpdateSeatRequest request) {

        UpdateSeatResponse response = new UpdateSeatResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateSeatResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatResponse> deleteSeat(@RequestBody DeleteSeatRequest request) {

        DeleteSeatResponse response = new DeleteSeatResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.OK);
    }
}
