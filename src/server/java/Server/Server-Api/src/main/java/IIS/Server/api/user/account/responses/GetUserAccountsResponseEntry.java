package IIS.Server.api.user.account.responses;

import lombok.Data;

@Data
public class GetUserAccountsResponseEntry {
    Integer id;
    String firstName;
    String lastName;
    String email;
}
