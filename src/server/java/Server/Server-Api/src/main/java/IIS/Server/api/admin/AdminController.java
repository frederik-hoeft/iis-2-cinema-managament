package IIS.Server.api.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.admin.requests.GetMovieRevenueRequest;
import IIS.Server.api.admin.requests.GetScreeningRevenueRequest;
import IIS.Server.api.admin.responses.GetMovieRevenueResponse;
import IIS.Server.api.admin.responses.GetScreeningRevenueResponse;

@RestController
@RequestMapping(path="/admin", produces="application/json")
@CrossOrigin(origins="*")
public class AdminController {
    
    @PostMapping("/revenue/list-movies")
    public ResponseEntity<GetMovieRevenueResponse> getMovieRevenue(@RequestBody GetMovieRevenueRequest request) {

        GetMovieRevenueResponse response = new GetMovieRevenueResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetMovieRevenueResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/revenue/list-screenings")
    public ResponseEntity<GetScreeningRevenueResponse> getScreeningRevenue(@RequestBody GetScreeningRevenueRequest request) {

        GetScreeningRevenueResponse response = new GetScreeningRevenueResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetScreeningRevenueResponse>(response, HttpStatus.OK);
    }
}
