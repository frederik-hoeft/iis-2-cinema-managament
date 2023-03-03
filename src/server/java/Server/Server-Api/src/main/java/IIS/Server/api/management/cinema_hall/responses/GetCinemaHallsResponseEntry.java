package IIS.Server.api.management.cinema_hall.responses;

import lombok.Data;

@Data
public class GetCinemaHallsResponseEntry {
    Integer id;
    String name;
    boolean available;

    public GetCinemaHallsResponseEntry(Integer id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }
}
