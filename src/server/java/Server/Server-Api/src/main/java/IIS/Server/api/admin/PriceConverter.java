package IIS.Server.api.admin;

import generated.cinemaService.PriceCategory;
import generated.cinemaService.PriceCategoryBox;
import generated.cinemaService.PriceCategoryStalls;

public abstract class PriceConverter {
    private PriceConverter() {
        super();
    }

    public static Double getValue(PriceCategory price) {
        if (price instanceof PriceCategoryStalls) {
            return 10d;
        }
        if (price instanceof PriceCategoryBox) {
            return 12d;
        }
        return 16d;
    }
}
