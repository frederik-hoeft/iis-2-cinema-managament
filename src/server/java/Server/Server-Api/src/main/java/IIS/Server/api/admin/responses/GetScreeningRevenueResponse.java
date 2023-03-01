package IIS.Server.api.admin.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class GetScreeningRevenueResponse {
    boolean success;
    Optional<String> error;
    Double totalRevenue;
}
