package IIS.Server.api.user.account.responses;

import java.util.Optional;

import lombok.Data;

@Data
public class UserUpdateAccountResponse {
    boolean success;
    Optional<String> error;
}
