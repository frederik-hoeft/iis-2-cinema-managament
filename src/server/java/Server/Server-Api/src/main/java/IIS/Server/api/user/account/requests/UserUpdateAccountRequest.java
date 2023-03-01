package IIS.Server.api.user.account.requests;

import lombok.Data;

@Data
public class UserUpdateAccountRequest {
    String email;
    String firstName;
    String lastName;
}
