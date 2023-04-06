package IIS.Server.api.admin.revenue.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class GetScreeningRevenueResponse {
    Boolean success;
    Optional<String> error;
    Double totalRevenue;
}
