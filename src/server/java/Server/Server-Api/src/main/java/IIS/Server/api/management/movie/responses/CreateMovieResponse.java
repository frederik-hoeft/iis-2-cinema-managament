package IIS.Server.api.management.movie.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class CreateMovieResponse {
    boolean success;
    Optional<String> error;
}
