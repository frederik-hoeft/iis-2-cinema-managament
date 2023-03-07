package IIS.Server.api.user.account.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UserUpdateAccountResponse {
    Boolean success;
    Optional<String> error;
}
