package IIS.Server.api.user.booking.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetUserBookingsResponse {
    boolean success;
    Optional<String> error;
    Collection<GetUserBookingsResponseEntry> bookings;
}
