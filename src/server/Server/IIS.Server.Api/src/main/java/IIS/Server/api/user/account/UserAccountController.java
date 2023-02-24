package IIS.Server.api.user.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.user.account.requests.UserCreateAccountRequest;
import IIS.Server.api.user.account.responses.UserCreateAccountResponse;

@RestController
@RequestMapping(path="/user/account", produces="application/json")
@CrossOrigin(origins="*")
public class UserAccountController {
    
    @PostMapping("/create")
    public ResponseEntity<UserCreateAccountResponse> createAccount(@RequestBody UserCreateAccountRequest request) {

        UserCreateAccountResponse response = new UserCreateAccountResponse();
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
