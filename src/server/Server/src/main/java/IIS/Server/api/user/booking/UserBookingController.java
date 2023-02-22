package IIS.Server.api.user.booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.user.booking.requests.*;
import IIS.Server.api.user.booking.responses.*;


@RestController
@RequestMapping(path="/user-booking", produces="application/json")
@CrossOrigin(origins="*")
public class UserBookingController {
    
    @PostMapping("/book")
    public ResponseEntity<UserBookingResponse> book(@RequestBody UserBookingRequest request) {

        UserBookingResponse response = new UserBookingResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserBookingResponse>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/reserve")
    public ResponseEntity<UserReservationResponse> reserve(@RequestBody UserReservationRequest request) {

        UserReservationResponse response = new UserReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserReservationResponse>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/upgrade")
    public ResponseEntity<UserUpgradeReservationResponse> upgradeReservation(@RequestBody UserUpgradeReservationRequest request) {

        UserUpgradeReservationResponse response = new UserUpgradeReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserUpgradeReservationResponse>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/cancel")
    public ResponseEntity<UserCancelReservationResponse> cancelReservation(@RequestBody UserCancelReservationRequest request) {

        UserCancelReservationResponse response = new UserCancelReservationResponse();
        response.setSuccess(false);
        return new ResponseEntity<UserCancelReservationResponse>(response, HttpStatus.I_AM_A_TEAPOT);
    }
}
