package IIS.Server.api.user.account.requests;

import lombok.Data;

@Data
public class UserCreateAccountRequest {
    String firstName;
    String lastName;
    String email;
}
