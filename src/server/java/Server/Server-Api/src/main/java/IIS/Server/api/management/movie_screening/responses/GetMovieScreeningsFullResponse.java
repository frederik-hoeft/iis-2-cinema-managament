package IIS.Server.api.management.movie_screening.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetMovieScreeningsFullResponse {
    boolean success;
    Optional<String> error;
    Collection<GetMovieScreeningsFullResponseEntry> screenings;
}
