package IIS.Server.api.user.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.BaseController;
import IIS.Server.api.user.account.requests.*;
import IIS.Server.api.user.account.responses.*;

@RestController
@RequestMapping(path="/user/account", produces="application/json")
@CrossOrigin(origins="*")
public class UserAccountController extends BaseController 
{
    @GetMapping("/list")
    public ResponseEntity<GetUserAccountsResponse> listAccounts() 
    {
        return scheduled(() ->
        {
            GetUserAccountsResponse response = new GetUserAccountsResponse();
            response.setSuccess(false);
            return new ResponseEntity<GetUserAccountsResponse>(response, HttpStatus.CREATED);
        });
    }

    @PostMapping("/create")
    public ResponseEntity<UserCreateAccountResponse> createAccount(@RequestBody UserCreateAccountRequest request) 
    {
        return scheduled(() ->
        {
            UserCreateAccountResponse response = new UserCreateAccountResponse();
            response.setSuccess(false);
            return new ResponseEntity<UserCreateAccountResponse>(response, HttpStatus.CREATED);
        });
    }

    @PostMapping("/get")
    public ResponseEntity<GetUserAccountResponse> getAccount(@RequestBody GetUserAccountRequest request) 
    {
        return scheduled(() ->
        {
            GetUserAccountResponse response = new GetUserAccountResponse();
            response.setSuccess(false);
            return new ResponseEntity<GetUserAccountResponse>(response, HttpStatus.CREATED);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<UserDeleteAccountResponse> deleteAccount(@RequestBody UserDeleteAccountRequest request) 
    {
        return scheduled(() ->
        {
            UserDeleteAccountResponse response = new UserDeleteAccountResponse();
            response.setSuccess(false);
            return new ResponseEntity<UserDeleteAccountResponse>(response, HttpStatus.CREATED);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UserUpdateAccountResponse> updateAccount(@RequestBody UserUpdateAccountRequest request) 
    {
        return scheduled(() ->
        {
            UserUpdateAccountResponse response = new UserUpdateAccountResponse();
            response.setSuccess(false);
            return new ResponseEntity<UserUpdateAccountResponse>(response, HttpStatus.CREATED);
        });
    }
}
