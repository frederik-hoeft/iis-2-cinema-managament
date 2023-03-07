package IIS.Server.api.user.booking.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetUserReservationsResponse {
    Boolean success;
    Optional<String> error;
    Collection<GetUserReservationsResponseEntry> reservations;
}
