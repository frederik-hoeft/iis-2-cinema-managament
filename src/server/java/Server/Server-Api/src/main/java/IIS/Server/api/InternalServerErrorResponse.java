package IIS.Server.api;

import java.util.Optional;

import lombok.Data;

@Data
public class InternalServerErrorResponse {
    Boolean success;
    Optional<String> error;
}
