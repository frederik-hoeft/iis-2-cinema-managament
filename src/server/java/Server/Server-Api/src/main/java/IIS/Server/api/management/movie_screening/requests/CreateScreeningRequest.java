package IIS.Server.api.management.movie_screening.requests;

import lombok.Data;

@Data
public class CreateScreeningRequest {
    Integer movieId;
    Integer cinemaHallId;
    String name;
    boolean hasExpired;
}
