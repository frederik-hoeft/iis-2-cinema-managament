/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.SeatRow;
import java.util.Set;
public interface ICinemaHall extends Identifiable{
   public CinemaHall getTheObject();
   public Integer getId();
   public Set<SeatRow> getRows() throws PersistenceException;
   public void addToRows(SeatRow arg) throws PersistenceException;
   public boolean removeFromRows(SeatRow arg) throws PersistenceException;
   public Boolean getAvailable() ;
   public void setAvailable(Boolean newAvailable) throws PersistenceException;
}
