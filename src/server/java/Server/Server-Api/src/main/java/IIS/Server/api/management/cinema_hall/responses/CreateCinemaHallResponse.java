package IIS.Server.api.management.cinema_hall.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class CreateCinemaHallResponse {
    Boolean success;
    Optional<String> error;
}
