package IIS.Server.api.user.booking.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UserReservationResponse {
    Boolean success;
    Optional<String> error;
}
