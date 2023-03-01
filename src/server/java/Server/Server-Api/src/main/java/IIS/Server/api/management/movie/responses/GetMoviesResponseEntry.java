package IIS.Server.api.management.movie.responses;

import lombok.Data;

@Data
public class GetMoviesResponseEntry {
    Integer id;
    String title;
    String description;
}
