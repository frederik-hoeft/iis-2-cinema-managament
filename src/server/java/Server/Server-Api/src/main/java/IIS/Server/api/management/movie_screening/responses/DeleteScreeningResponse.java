package IIS.Server.api.management.movie_screening.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class DeleteScreeningResponse {
    boolean success;
    Optional<String> error;
}
