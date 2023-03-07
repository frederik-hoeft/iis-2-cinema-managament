package IIS.Server.api.management.seat.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class DeleteSeatResponse {
    Boolean success;
    Optional<String> error;
}
