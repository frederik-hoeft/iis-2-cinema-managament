/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.CinemaHall;
import generated.cinema.SeatRow;
import java.util.Set;
public interface ICinemaHall extends Identifiable{
   public CinemaHall getTheObject();
   public Integer getId();
   public Set<SeatRow> getRows() throws PersistenceException;
   public void addToRows(SeatRow arg) throws PersistenceException;
   public boolean removeFromRows(SeatRow arg) throws PersistenceException;
   public Boolean getAvailable() ;
   public void setAvailable(Boolean newAvailable) throws PersistenceException;
   public Integer getRoomId() ;
   public void setRoomId(Integer newRoomId) throws PersistenceException;
}
