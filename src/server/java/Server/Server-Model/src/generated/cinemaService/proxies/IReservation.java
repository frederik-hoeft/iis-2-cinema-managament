/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.Reservation;
public interface IReservation extends IBookingState{
   public Reservation getTheObject();
   public Integer getId();
}