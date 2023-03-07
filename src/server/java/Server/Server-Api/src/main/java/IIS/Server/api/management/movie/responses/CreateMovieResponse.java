package IIS.Server.api.management.movie.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class CreateMovieResponse {
    Boolean success;
    Optional<String> error;
}
