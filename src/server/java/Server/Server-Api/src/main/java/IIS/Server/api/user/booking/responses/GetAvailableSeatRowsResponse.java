package IIS.Server.api.user.booking.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetAvailableSeatRowsResponse {
    Boolean success;
    Optional<String> error;
    Collection<GetAvailableSeatRowsResponseEntry> rows;
}
