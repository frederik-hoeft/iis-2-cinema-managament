package IIS.Server.api.user.booking.requests;

import lombok.Data;

@Data
public class UserReservationRequest {
    Integer screeningId;
    Integer seatId;
    String email;
}
