package IIS.Server.api.management.movie.responses;

import lombok.Data;

@Data
public class GetMoviesFullResponseEntry {
    Integer id;
    String title;
    String description;
    Integer screeningCount;
}
