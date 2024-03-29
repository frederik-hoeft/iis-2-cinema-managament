package IIS.Server.api.admin.revenue.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class GetMovieRevenueResponse {
    Boolean success;
    Optional<String> error;
    Double totalRevenue;
}
