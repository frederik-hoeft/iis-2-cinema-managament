package IIS.Server.api.management.movie_screening.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class CreateScreeningResponse {
    boolean success;
    Optional<String> error;
}
