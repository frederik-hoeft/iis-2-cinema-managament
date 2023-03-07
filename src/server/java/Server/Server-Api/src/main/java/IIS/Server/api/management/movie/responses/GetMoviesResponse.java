package IIS.Server.api.management.movie.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetMoviesResponse {
    Boolean success;
    Optional<String> error;
    Collection<GetMoviesResponseEntry> movies;
}