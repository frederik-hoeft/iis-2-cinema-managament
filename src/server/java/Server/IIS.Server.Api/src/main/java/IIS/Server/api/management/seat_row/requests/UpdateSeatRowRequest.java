package IIS.Server.api.management.seat_row.requests;

import lombok.Data;

@Data
public class UpdateSeatRowRequest {
    Integer seatRowId;
    Integer cinemaHallId;
}
