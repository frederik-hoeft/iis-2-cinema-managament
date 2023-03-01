package IIS.Server.api.user.account.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UserDeleteAccountResponse {
    boolean success;
    Optional<String> error;
}
