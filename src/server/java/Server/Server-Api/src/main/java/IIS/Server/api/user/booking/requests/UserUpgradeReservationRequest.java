package IIS.Server.api.user.booking.requests;

import lombok.Data;

@Data
public class UserUpgradeReservationRequest {
    Integer reservationId;
    String email;
}
