package IIS.Server.api.management.cinema_hall.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class DeleteCinemaHallResponse {
    boolean success;
    Optional<String> error;
}
