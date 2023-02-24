package IIS.Server.api.management.seat_row;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.seat_row.requests.*;
import IIS.Server.api.management.seat_row.responses.*;

@RestController
@RequestMapping(path="/management/seat-row", produces="application/json")
@CrossOrigin(origins="*")
public class SeatRowController {
    
    @GetMapping("/list")
    public ResponseEntity<GetSeatRowsResponse> getSeatRows() {

        GetSeatRowsResponse response = new GetSeatRowsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSeatRowResponse> createSeatRow(@RequestBody CreateSeatRowRequest request) {

        CreateSeatRowResponse response = new CreateSeatRowResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateSeatRowResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatRowResponse> updateSeatRow(@RequestBody UpdateSeatRowRequest request) {

        UpdateSeatRowResponse response = new UpdateSeatRowResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatRowResponse> deleteSeatRow(@RequestBody DeleteSeatRowRequest request) {

        DeleteSeatRowResponse response = new DeleteSeatRowResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.OK);
    }
}
