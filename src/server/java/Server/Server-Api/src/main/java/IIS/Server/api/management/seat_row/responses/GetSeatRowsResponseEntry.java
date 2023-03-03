package IIS.Server.api.management.seat_row.responses;

import generated.cinemaService.PriceCategory;
import lombok.Data;

@Data
public class GetSeatRowsResponseEntry {
    Integer id;
    String name;
    Integer seatCount;
    PriceCategory priceCategory;
}
