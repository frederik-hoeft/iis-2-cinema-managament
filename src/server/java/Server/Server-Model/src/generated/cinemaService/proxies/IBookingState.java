/**--- Generated at Tue Mar 07 22:26:22 CET 2023 
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
