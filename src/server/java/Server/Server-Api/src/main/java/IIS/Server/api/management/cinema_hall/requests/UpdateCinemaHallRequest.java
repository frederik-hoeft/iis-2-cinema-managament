package IIS.Server.api.management.cinema_hall.requests;

import lombok.Data;

@Data
public class UpdateCinemaHallRequest {
    Integer id;
    String newName;
    Boolean available;
}
