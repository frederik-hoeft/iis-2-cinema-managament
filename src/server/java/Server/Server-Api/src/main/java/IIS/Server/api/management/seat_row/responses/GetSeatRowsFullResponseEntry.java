package IIS.Server.api.management.seat_row.responses;

import java.util.Collection;

import IIS.Server.api.PriceCategoryEnum;
import IIS.Server.api.management.seat.responses.GetSeatsResponseEntry;
import lombok.Data;

@Data
public class GetSeatRowsFullResponseEntry {
    Integer id;
    String name;
    String cinemaHallName;
    PriceCategoryEnum price;
    Collection<GetSeatsResponseEntry> seats;
}
