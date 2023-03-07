package IIS.Server.api.management.seat_row.responses;

import IIS.Server.api.PriceCategoryEnum;
import lombok.Data;

@Data
public class GetSeatRowsResponseEntry {
    Integer id;
    String name;
    Integer seatCount;
    PriceCategoryEnum price;
}
