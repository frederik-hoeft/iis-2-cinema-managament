/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.Seat;
import generated.cinemaService.BookingState;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.cinemaService.SeatRow;
public interface ISeat extends Identifiable{
   public Seat getTheObject();
   public Integer getId();
   public Set<BookingState> getBookings() throws PersistenceException;
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public SeatRow getRow() throws PersistenceException;
}
