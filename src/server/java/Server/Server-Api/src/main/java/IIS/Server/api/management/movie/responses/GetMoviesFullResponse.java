package IIS.Server.api.management.movie.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetMoviesFullResponse {
    boolean success;
    Optional<String> error;
    Collection<GetMoviesFullResponseEntry> movies;
}
