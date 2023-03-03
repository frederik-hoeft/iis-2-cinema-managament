package IIS.Server.api.management.cinema_hall.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetCinemaHallsFullResponse {
    boolean success;
    Optional<String> error;
    Collection<GetCinemaHallsFullResponseEntry> cinemaHalls;
}
