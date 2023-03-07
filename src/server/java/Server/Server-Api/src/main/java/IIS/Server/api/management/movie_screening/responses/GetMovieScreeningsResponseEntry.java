package IIS.Server.api.management.movie_screening.responses;

import lombok.Data;

@Data
public class GetMovieScreeningsResponseEntry {
    Integer id;
    String name;
    String movieTitle;
    Boolean finished;
}
