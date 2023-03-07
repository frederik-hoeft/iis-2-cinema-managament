package IIS.Server.api.management.movie_screening.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UpdateScreeningResponse {
    Boolean success;
    Optional<String> error;
}
