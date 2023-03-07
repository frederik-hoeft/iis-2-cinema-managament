package IIS.Server.api.admin.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class GetScreeningRevenueResponse {
    Boolean success;
    Optional<String> error;
    Double totalRevenue;
}
