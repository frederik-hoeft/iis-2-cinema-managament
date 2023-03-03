package IIS.Server.api.management.seat_row.requests;

import generated.cinemaService.PriceCategory;
import lombok.Data;

@Data
public class UpdateSeatRowRequest {
    Integer id;
    String name;
    PriceCategory priceCategory;
}
