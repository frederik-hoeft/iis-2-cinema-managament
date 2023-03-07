package IIS.Server.api.management.seat_row.requests;

import IIS.Server.api.PriceCategoryEnum;
import lombok.Data;

@Data
public class CreateSeatRowRequest {
    Integer cinemaHallId;
    String name;
    PriceCategoryEnum priceCategory;
}
