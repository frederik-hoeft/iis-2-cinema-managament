package IIS.Server.api.user.account.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UserCreateAccountResponse {
    boolean success;
    Optional<String> error;
}
