/**--- Generated at Tue Mar 07 13:35:38 CET 2023 
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
   public void setScreening(MovieScreening newScreening)throws PersistenceException;
   public Seat getSeat() throws PersistenceException;
   public void setSeat(Seat newSeat)throws PersistenceException;
   public Customer getCustomer() throws PersistenceException;
   public void setCustomer(Customer newCustomer)throws PersistenceException;
}
