/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.Seat;
import generated.cinema.SeatRow;
import generated.cinema.BookingState;
import java.util.Optional;
public interface ISeat extends Identifiable{
   public Seat getTheObject();
   public Integer getId();
   public SeatRow getRow() throws PersistenceException;
   public void setRow(SeatRow newRow)throws PersistenceException;
   public Optional<BookingState> getBookingState() throws PersistenceException;
   public void setBookingState(BookingState newBookingState)throws PersistenceException;
   public Integer getSeatId() ;
   public void setSeatId(Integer newSeatId) throws PersistenceException;
}
