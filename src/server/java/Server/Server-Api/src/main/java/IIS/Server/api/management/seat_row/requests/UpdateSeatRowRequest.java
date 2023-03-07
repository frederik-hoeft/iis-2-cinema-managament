package IIS.Server.api.management.seat_row.requests;

import IIS.Server.api.PriceCategoryEnum;
import lombok.Data;

@Data
public class UpdateSeatRowRequest {
    Integer id;
    String name;
    PriceCategoryEnum priceCategory;
}
