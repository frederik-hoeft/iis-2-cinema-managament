package IIS.Server.api.admin.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class GetMovieRevenueResponse {
    boolean success;
    Optional<String> error;
    Double totalRevenue;
}
