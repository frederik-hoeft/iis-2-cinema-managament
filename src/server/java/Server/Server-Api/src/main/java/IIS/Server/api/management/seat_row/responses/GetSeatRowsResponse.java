package IIS.Server.api.management.seat_row.responses;

import java.util.Collection;
import java.util.Optional;


import lombok.Data;

@Data
public class GetSeatRowsResponse {
    boolean success;
    Optional<String> error;
    Collection<GetSeatRowsResponseEntry> rows;
}
