/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.BookingState;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Seat;
import generated.cinemaService.Customer;
public interface IBookingState extends Identifiable{
   public BookingState getTheObject();
   public MovieScreening getScreening() throws PersistenceException;
   public Seat getSeat() throws PersistenceException;
   public Customer getCustomer() throws PersistenceException;
}
