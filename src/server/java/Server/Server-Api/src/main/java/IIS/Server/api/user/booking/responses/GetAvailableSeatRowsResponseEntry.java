package IIS.Server.api.user.booking.responses;

import IIS.Server.api.PriceCategoryEnum;
import lombok.Data;

@Data
public class GetAvailableSeatRowsResponseEntry {
    Integer rowId;
    String name;
    Integer availableSeatCount;
    PriceCategoryEnum priceCategory;
}
