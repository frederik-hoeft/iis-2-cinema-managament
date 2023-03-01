package IIS.Server.api.management.movie.requests;

import lombok.Data;

@Data
public class CreateMovieRequest {
    String title;
    String description;
}
