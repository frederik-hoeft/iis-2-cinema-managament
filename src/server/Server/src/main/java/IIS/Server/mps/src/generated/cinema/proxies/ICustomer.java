/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.Customer;
import generated.cinema.BookingState;
import java.util.Set;
public interface ICustomer extends Identifiable{
   public Customer getTheObject();
   public Integer getId();
   public Set<BookingState> getBookings() throws PersistenceException;
   public void addToBookings(BookingState arg) throws PersistenceException;
   public boolean removeFromBookings(BookingState arg) throws PersistenceException;
   public Integer getCustomerId() ;
   public void setCustomerId(Integer newCustomerId) throws PersistenceException;
   public String getFirstName() ;
   public void setFirstName(String newFirstName) throws PersistenceException;
   public String getLastName() ;
   public void setLastName(String newLastName) throws PersistenceException;
   public String getEmail() ;
   public void setEmail(String newEmail) throws PersistenceException;
}
