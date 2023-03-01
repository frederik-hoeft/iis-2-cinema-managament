package IIS.Server.api.management.movie_screening.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetScreeningsResponse {
    boolean success;
    Optional<String> error;
    Collection<GetMovieScreeningsResponseEntry> screenings;
}
