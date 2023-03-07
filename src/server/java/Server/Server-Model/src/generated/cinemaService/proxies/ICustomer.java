/**--- Generated at Wed Mar 08 00:30:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.Customer;
import generated.cinemaService.BookingState;
import java.util.Set;
import exceptions.ConstraintViolation;
public interface ICustomer extends Identifiable{
   public Customer getTheObject();
   public Integer getId();
   public Set<BookingState> getBookings() throws PersistenceException;
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException;
   public String getFirstName() ;
   public void setFirstName(String newFirstName) throws PersistenceException;
   public String getLastName() ;
   public void setLastName(String newLastName) throws PersistenceException;
   public String getEmail() ;
   public void setEmail(String newEmail) throws PersistenceException;
}
