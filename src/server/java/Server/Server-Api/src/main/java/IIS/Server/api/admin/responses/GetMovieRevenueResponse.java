package IIS.Server.api.admin.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class GetMovieRevenueResponse {
    Boolean success;
    Optional<String> error;
    Double totalRevenue;
}
