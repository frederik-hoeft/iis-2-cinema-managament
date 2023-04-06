package IIS.Server.api.user.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestvike.linq.Linq;

import IIS.Server.api.BaseController;
import IIS.Server.api.Response;
import IIS.Server.api.user.account.requests.*;
import IIS.Server.api.user.account.responses.*;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.Booking;
import generated.cinemaService.BookingState;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Customer;
import generated.cinemaService.Reservation;
import generated.cinemaService.proxies.ICustomer;

@RestController
@RequestMapping(path="/user/account", produces="application/json")
@CrossOrigin(origins="*")
public class AccountController extends BaseController 
{
    @GetMapping("/list")
    public ResponseEntity<GetUserAccountsResponse> listAccounts() 
    {
        return isolated(() ->
        {
            final var users = ObjectX.createFromMany(CinemaService.getSetOf(ICustomer.class), GetUserAccountsResponseEntry.class);
            GetUserAccountsResponse response = new GetUserAccountsResponse();
            response.setSuccess(true);
            response.setAccounts(users);
            return new ResponseEntity<GetUserAccountsResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/create")
    public ResponseEntity<UserCreateAccountResponse> createAccount(@RequestBody UserCreateAccountRequest request) 
    {
        return isolated(() ->
        {
            if (Linq.of(CinemaService.getSetOf(ICustomer.class)).any(c -> c.getEmail().equalsIgnoreCase(request.getEmail())))
            {
                return Response.error(UserCreateAccountResponse.class, "The specified user already exists!");
            }
            Customer.createFresh(request.getFirstName(), request.getLastName(), request.getEmail().toLowerCase());
            UserCreateAccountResponse response = new UserCreateAccountResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserCreateAccountResponse>(response, HttpStatus.CREATED);
        });
    }

    @PostMapping("/get")
    public ResponseEntity<GetUserAccountResponse> getAccount(@RequestBody GetUserAccountRequest request) 
    {
        return isolated(() ->
        {
            final ICustomer user = Linq.of(CinemaService.getSetOf(ICustomer.class)).firstOrDefault(c -> c.getEmail().equalsIgnoreCase(request.getEmail()));
            if (user == null)
            {
                return Response.error(GetUserAccountResponse.class, "the specified user does not exist");
            }
            GetUserAccountResponse response = new GetUserAccountResponse();
            response.setSuccess(true);
            response.setAccount(ObjectX.createFrom(user, GetUserAccountsResponseEntry.class));
            return new ResponseEntity<GetUserAccountResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<UserDeleteAccountResponse> deleteAccount(@RequestBody UserDeleteAccountRequest request) 
    {
        return isolated(() ->
        {
            final ICustomer user = Linq.of(CinemaService.getSetOf(ICustomer.class)).firstOrDefault(c -> c.getEmail().equalsIgnoreCase(request.getEmail()));
            if (user == null)
            {
                return Response.error(UserDeleteAccountResponse.class, "the specified user does not exist");
            }
            try 
            {
                for (BookingState booking : user.getBookings())
                {
                    if (booking instanceof Booking)
                    {
                        Booking.delete(booking.getId());
                    }
                    else
                    {
                        Reservation.delete(booking.getId());
                    }
                }
                Customer.delete(user.getId());
            } 
            catch (Exception e) 
            {
                return Response.error(e);
            }
            UserDeleteAccountResponse response = new UserDeleteAccountResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserDeleteAccountResponse>(response, HttpStatus.OK);
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UserUpdateAccountResponse> updateAccount(@RequestBody UserUpdateAccountRequest request) 
    {
        return isolated(() ->
        {
            final ICustomer user = Linq.of(CinemaService.getSetOf(ICustomer.class)).firstOrDefault(c -> c.getEmail().equalsIgnoreCase(request.getEmail()));
            if (user == null)
            {
                return Response.error(UserUpdateAccountResponse.class, "the specified user does not exist");
            }
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            UserUpdateAccountResponse response = new UserUpdateAccountResponse();
            response.setSuccess(true);
            return new ResponseEntity<UserUpdateAccountResponse>(response, HttpStatus.OK);
        });
    }
}
