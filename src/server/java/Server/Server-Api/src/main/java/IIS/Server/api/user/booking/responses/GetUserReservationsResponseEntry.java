package IIS.Server.api.user.booking.responses;

import IIS.Server.api.management.movie_screening.responses.GetMovieScreeningsFullResponseEntry;
import IIS.Server.api.management.seat.responses.GetSeatsFullResponseEntry;
import IIS.Server.api.user.account.responses.GetUserAccountsResponseEntry;
import lombok.Data;

@Data
public class GetUserReservationsResponseEntry {
    Integer id;
    GetMovieScreeningsFullResponseEntry movieScreening;
    GetSeatsFullResponseEntry seat;
    GetUserAccountsResponseEntry user;
}
