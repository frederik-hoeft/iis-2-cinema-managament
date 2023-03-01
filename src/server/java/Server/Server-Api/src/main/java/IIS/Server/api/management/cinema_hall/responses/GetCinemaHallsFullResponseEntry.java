package IIS.Server.api.management.cinema_hall.responses;

import java.util.Collection;

import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import lombok.Data;

@Data
public class GetCinemaHallsFullResponseEntry {
    Integer id;
    String name;
    Collection<GetSeatRowsResponseEntry> rows;
}
