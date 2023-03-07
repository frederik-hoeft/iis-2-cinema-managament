package IIS.Server.api.management.movie_screening.responses;

import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.movie.responses.GetMoviesResponseEntry;
import lombok.Data;

@Data
public class GetMovieScreeningsFullResponseEntry {
    Integer id;
    String name;
    GetMoviesResponseEntry movie;
    GetCinemaHallsResponseEntry hall;
    Boolean finished;
}
