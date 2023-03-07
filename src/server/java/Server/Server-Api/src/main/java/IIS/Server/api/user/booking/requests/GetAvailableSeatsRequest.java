package IIS.Server.api.user.booking.requests;

import lombok.Data;

@Data
public class GetAvailableSeatsRequest {
    Integer rowId;
    Integer screeningId;
}
