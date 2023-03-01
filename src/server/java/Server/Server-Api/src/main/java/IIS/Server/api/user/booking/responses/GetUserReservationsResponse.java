package IIS.Server.api.user.booking.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetUserReservationsResponse {
    boolean success;
    Optional<String> error;
    Collection<GetUserReservationsResponseEntry> reservations;
}
