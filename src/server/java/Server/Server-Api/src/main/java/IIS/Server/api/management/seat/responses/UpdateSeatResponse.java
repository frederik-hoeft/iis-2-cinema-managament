package IIS.Server.api.management.seat.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UpdateSeatResponse {
    Boolean success;
    Optional<String> error;
}
