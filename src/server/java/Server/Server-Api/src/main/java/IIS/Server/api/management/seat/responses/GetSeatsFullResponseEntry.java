package IIS.Server.api.management.seat.responses;

import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import lombok.Data;

@Data
public class GetSeatsFullResponseEntry {
    Integer id;
    String name;
    GetSeatRowsResponseEntry row;
}
