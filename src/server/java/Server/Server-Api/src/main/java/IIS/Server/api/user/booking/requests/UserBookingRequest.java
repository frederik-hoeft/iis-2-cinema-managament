package IIS.Server.api.user.booking.requests;

import lombok.Data;

@Data
public class UserBookingRequest {
    Integer screeningId;
    Integer seatId;
}
