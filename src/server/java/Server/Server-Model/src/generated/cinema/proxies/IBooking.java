/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.Booking;
public interface IBooking extends IBookingState{
   public Booking getTheObject();
   public Integer getId();
   public Integer getBookingId() ;
   public void setBookingId(Integer newBookingId) throws PersistenceException;
}
