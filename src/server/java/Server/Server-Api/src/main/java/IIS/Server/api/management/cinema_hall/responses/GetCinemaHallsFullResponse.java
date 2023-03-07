package IIS.Server.api.management.cinema_hall.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetCinemaHallsFullResponse {
    Boolean success;
    Optional<String> error;
    Collection<GetCinemaHallsFullResponseEntry> cinemaHalls;
}
