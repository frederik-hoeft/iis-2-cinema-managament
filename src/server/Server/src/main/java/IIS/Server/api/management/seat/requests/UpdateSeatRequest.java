package IIS.Server.api.management.seat.requests;

import lombok.Data;

@Data
public class UpdateSeatRequest {
    Integer seatId;
    Integer seatRowId;
}
