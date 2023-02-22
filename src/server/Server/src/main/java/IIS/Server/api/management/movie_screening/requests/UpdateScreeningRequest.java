package IIS.Server.api.management.movie_screening.requests;

import lombok.Data;

@Data
public class UpdateScreeningRequest {
    Integer screeningId;
    Integer movieId;
    Integer cinemaHallId;
}
