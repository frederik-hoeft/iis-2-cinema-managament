package IIS.Server.api.management.seat_row.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetSeatRowsFullResponse {
    boolean success;
    Optional<String> error;
    Collection<GetSeatRowsFullResponseEntry> rows;
}
