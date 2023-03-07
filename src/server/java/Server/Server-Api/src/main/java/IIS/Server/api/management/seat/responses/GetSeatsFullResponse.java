package IIS.Server.api.management.seat.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetSeatsFullResponse {
    Boolean success;
    Optional<String> error;
    Collection<GetSeatsFullResponseEntry> seats;
}
