package IIS.Server.api;

import generated.cinemaService.PriceCategory;
import generated.cinemaService.PriceCategoryBox;
import generated.cinemaService.PriceCategoryServiceBox;
import generated.cinemaService.PriceCategoryStalls;

public enum PriceCategoryEnum 
{
    INVALID(0),
    STALLS(1),
    BOX(2),
    BOX_SERVICE(3);

    public final Integer value;

    private PriceCategoryEnum(Integer value) 
    {
        this.value = value;
    }

    public PriceCategory toMpsUnsafe()
    {
        return switch (value) 
        {
            case 1 -> PriceCategoryStalls.getInstance();
            case 2 -> PriceCategoryBox.getInstance();
            case 3 -> PriceCategoryServiceBox.getInstance();
            default -> null;
        };
    }

    public static PriceCategoryEnum from(PriceCategory mps)
    {
        if (mps instanceof PriceCategoryStalls)
        {
            return STALLS;
        }
        if (mps instanceof PriceCategoryBox)
        {
            return BOX;
        }
        if (mps instanceof PriceCategoryServiceBox)
        {
            return BOX_SERVICE;
        }
        return INVALID;
    }
}
