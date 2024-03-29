package IIS.Server.api.admin.revenue;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestvike.linq.Linq;

import IIS.Server.api.BaseController;
import IIS.Server.api.Response;
import IIS.Server.api.admin.revenue.requests.GetMovieRevenueRequest;
import IIS.Server.api.admin.revenue.requests.GetScreeningRevenueRequest;
import IIS.Server.api.admin.revenue.responses.GetScreeningRevenueResponse;
import IIS.Server.api.admin.revenue.responses.GetMovieRevenueResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponse;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsResponseEntry;
import IIS.Server.api.management.movie_screening.responses.GetScreeningsResponse;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaService;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.proxies.IMovie;
import generated.cinemaService.proxies.IMovieScreening;

@RestController
@RequestMapping(path="/admin/revenue", produces="application/json")
@CrossOrigin(origins="*")
public class RevenueController extends BaseController
{
    @PostMapping("/movie")
    public ResponseEntity<GetMovieRevenueResponse> getMovieRevenue(@RequestBody GetMovieRevenueRequest request) 
    {
        return isolated(() -> 
        {
            final var movie = CinemaService.getCacheOf(IMovie.class).getOrDefault(request.getMovieId(), null);
            if (movie == null) 
            {
                return Response.error(GetMovieRevenueResponse.class, "Could not find movie for id " + request.getMovieId());
            }
            Double total = 0d;
            for (MovieScreening screening : movie.getScreenings()) 
            {
                for (BookingState booking : screening.getBookings()) 
                {
                    if (booking instanceof Booking)
                    {
                        final Double ticketPrice = PriceConverter.getValue(booking.getSeat().getRow().getPrice());
                        total += ticketPrice;
                    }
                }
            }
            GetMovieRevenueResponse response = new GetMovieRevenueResponse();
            response.setTotalRevenue(total);
            response.setSuccess(true);
            return new ResponseEntity<GetMovieRevenueResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/screening")
    public ResponseEntity<GetScreeningRevenueResponse> getScreeningRevenue(@RequestBody GetScreeningRevenueRequest request) 
    {
        return isolated(() -> 
        {
            final var screening = CinemaService.getCacheOf(IMovieScreening.class).getOrDefault(request.getScreeningId(), null);
            if (screening == null) 
            {
                return Response.error(GetScreeningRevenueResponse.class, "Could not find movie screening for id " + request.getScreeningId());
            }
            Double total = 0d;
            for (BookingState booking : screening.getBookings()) 
            {
                if (booking instanceof Booking)
                {
                    final Double ticketPrice = PriceConverter.getValue(booking.getSeat().getRow().getPrice());
                    total += ticketPrice;
                }
            }

            GetScreeningRevenueResponse response = new GetScreeningRevenueResponse();
            response.setTotalRevenue(total);
            response.setSuccess(true);
            return new ResponseEntity<GetScreeningRevenueResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-movies")
    public ResponseEntity<GetMoviesResponse> getMovieList() 
    {
        return isolated(() -> 
        {
            final var movies = Linq.of(CinemaService.getSetOf(IMovie.class))
                //.where(m -> Linq.of(m.getScreenings()).any(s -> s.getFinished()))
                .select(m -> ObjectX.createFrom(m, GetMoviesResponseEntry.class))
                .toList();
            GetMoviesResponse response = new GetMoviesResponse();
            response.setMovies(movies);
            response.setSuccess(true);
            return new ResponseEntity<GetMoviesResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-screenings")
    public ResponseEntity<GetScreeningsResponse> getScreeningList() 
    {
        return isolated(() -> 
        {
            final var screenings = Linq.of(CinemaService.getSetOf(IMovieScreening.class))
                //.where(s -> s.getFinished())
                .select(s -> 
                {
                    final var screening = ObjectX.createFrom(s, GetMovieScreeningsResponseEntry.class);
                    screening.setMovieTitle(s.getMovie().getTitle());
                    return screening;
                })
                .toList();
            GetScreeningsResponse response = new GetScreeningsResponse();
            response.setScreenings(screenings);
            response.setSuccess(true);
            return new ResponseEntity<GetScreeningsResponse>(response, HttpStatus.OK);
        });
    }
}
