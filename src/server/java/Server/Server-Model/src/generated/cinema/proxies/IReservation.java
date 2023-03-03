/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.Reservation;
public interface IReservation extends IBookingState{
   public Reservation getTheObject();
   public Integer getId();
   public Integer getReservationId() ;
   public void setReservationId(Integer newReservationId) throws PersistenceException;
}
