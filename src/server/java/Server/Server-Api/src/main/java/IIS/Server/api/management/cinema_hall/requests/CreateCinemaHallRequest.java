package IIS.Server.api.management.cinema_hall.requests;

import lombok.Data;

@Data
public class CreateCinemaHallRequest {
    String name;
    boolean available;
}
