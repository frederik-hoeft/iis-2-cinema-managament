package IIS.Server.api.user.booking.responses;

import generated.cinema.PriceCategory;
import lombok.Data;

@Data
public class GetAvailableSeatRowsResponseEntry {
    Integer rowId;
    String name;
    Integer availableSeatCount;
    PriceCategory priceCategory;
}
