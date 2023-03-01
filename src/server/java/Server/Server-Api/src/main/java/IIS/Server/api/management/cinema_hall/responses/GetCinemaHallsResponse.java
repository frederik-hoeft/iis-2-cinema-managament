package IIS.Server.api.management.cinema_hall.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetCinemaHallsResponse {
    boolean success;
    Optional<String> error;
    Collection<GetCinemaHallsResponseEntry> cinemaHalls;
}
