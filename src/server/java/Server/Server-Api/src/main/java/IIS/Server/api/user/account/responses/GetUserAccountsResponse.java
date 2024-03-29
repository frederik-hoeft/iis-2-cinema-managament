package IIS.Server.api.user.account.responses;

import java.util.Collection;
import java.util.Optional;

import lombok.Data;

@Data
public class GetUserAccountsResponse {
    Boolean success;
    Optional<String> error;
    Collection<GetUserAccountsResponseEntry> accounts;
}
