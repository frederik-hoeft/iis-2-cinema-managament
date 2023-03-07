package IIS.Server.api.management.movie.requests;

import lombok.Data;

@Data
public class UpdateMovieRequest {
    Integer id;
    String title;
    String description;
}
