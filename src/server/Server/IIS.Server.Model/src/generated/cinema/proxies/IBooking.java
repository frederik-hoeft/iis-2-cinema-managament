/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
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
