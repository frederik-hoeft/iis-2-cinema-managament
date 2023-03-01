package IIS.Server.api.management.movie.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class DeleteMovieResponse {
    boolean success;
    Optional<String> error;
}
