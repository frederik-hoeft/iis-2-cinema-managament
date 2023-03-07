package IIS.Server.api.management.seat_row.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class DeleteSeatRowResponse {
    Boolean success;
    Optional<String> error;
}
