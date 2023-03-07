package IIS.Server.api.management.seat_row.responses;

import java.util.Collection;

import IIS.Server.api.management.seat.responses.GetSeatsResponseEntry;
import generated.cinemaService.PriceCategory;
import lombok.Data;

@Data
public class GetSeatRowsFullResponseEntry {
    Integer id;
    String name;
    String cinemaHallName;
    PriceCategory price;
    Collection<GetSeatsResponseEntry> seats;
}
