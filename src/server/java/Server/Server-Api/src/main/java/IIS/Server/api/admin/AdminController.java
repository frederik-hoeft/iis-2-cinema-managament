package IIS.Server.api.admin;

import java.math.BigInteger;
import java.util.Optional;

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
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import baseTypes.Rational;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;

@RestController
@RequestMapping(path="/admin", produces="application/json")
@CrossOrigin(origins="*")
public class AdminController {
    
    @PostMapping("/revenue/list-movies")
    public ResponseEntity<GetMovieRevenueResponse> getMovieRevenue(@RequestBody GetMovieRevenueRequest request) {

        AsyncWorkload<ResponseEntity<GetMovieRevenueResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getMovieCache().containsKey(request.getMovieId())) {
                GetMovieRevenueResponse response = new GetMovieRevenueResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find movie for id " + request.getMovieId()));
                return new ResponseEntity<GetMovieRevenueResponse>(response, HttpStatus.BAD_REQUEST);
            }

            Movie movie = CinemaService.getInstance().getMovie(request.getMovieId());
            Rational revenue = new Rational(0);
            for (MovieScreening screening : movie.getScreenings()) {
                for (BookingState booking : screening.getBookings()) {
                    revenue = Rational.add(revenue, booking.getSeat().getRow().getPrice().getPrice().get());
                }
            }

            Double total = revenue.getEnumerator().divide(revenue.getDenominator()).doubleValue();

            GetMovieRevenueResponse response = new GetMovieRevenueResponse();
            response.setTotalRevenue(total);
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetMovieRevenueResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetMovieRevenueResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/revenue/list-screenings")
    public ResponseEntity<GetScreeningRevenueResponse> getScreeningRevenue(@RequestBody GetScreeningRevenueRequest request) {

        AsyncWorkload<ResponseEntity<GetScreeningRevenueResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getMovieScreeningCache().containsKey(request.getScreeningId())) {
                GetScreeningRevenueResponse response = new GetScreeningRevenueResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find movie screening for id " + request.getScreeningId()));
                return new ResponseEntity<GetScreeningRevenueResponse>(response, HttpStatus.BAD_REQUEST);
            }

            MovieScreening screening = CinemaService.getInstance().getMovieScreening(request.getScreeningId());
            Rational revenue = new Rational(0);
            for (BookingState booking : screening.getBookings()) {
                revenue = Rational.add(revenue, booking.getSeat().getRow().getPrice().getPrice().get());
            }

            Double total = revenue.getEnumerator().divide(revenue.getDenominator()).doubleValue();

            GetScreeningRevenueResponse response = new GetScreeningRevenueResponse();
            response.setTotalRevenue(total);
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetScreeningRevenueResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetScreeningRevenueResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }
}
