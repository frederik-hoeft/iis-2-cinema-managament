package IIS.Server.api.user.booking.requests;

import lombok.Data;

@Data
public class UserCancelReservationRequest {
    Integer reservationId;
}
