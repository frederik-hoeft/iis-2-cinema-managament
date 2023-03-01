/**--- Generated at Wed Mar 01 16:05:46 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.BookingState;
import generated.cinema.MovieScreening;
import generated.cinema.Seat;
import generated.cinema.Customer;
public interface IBookingState extends Identifiable{
   public BookingState getTheObject();
   public MovieScreening getScreening() throws PersistenceException;
   public void setScreening(MovieScreening newScreening)throws PersistenceException;
   public Seat getSeat() throws PersistenceException;
   public void setSeat(Seat newSeat)throws PersistenceException;
   public Customer getCustomer() throws PersistenceException;
   public void setCustomer(Customer newCustomer)throws PersistenceException;
}
