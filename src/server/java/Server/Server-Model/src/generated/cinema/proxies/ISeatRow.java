/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.SeatRow;
import generated.cinema.CinemaHall;
import generated.cinema.PriceCategory;
import generated.cinema.Seat;
import java.util.Set;
public interface ISeatRow extends Identifiable{
   public SeatRow getTheObject();
   public Integer getId();
   public CinemaHall getHall() throws PersistenceException;
   public void setHall(CinemaHall newHall)throws PersistenceException;
   public PriceCategory getPrice() throws PersistenceException;
   public void setPrice(PriceCategory newPrice)throws PersistenceException;
   public Set<Seat> getSeats() throws PersistenceException;
   public void addToSeats(Seat arg) throws PersistenceException;
   public boolean removeFromSeats(Seat arg) throws PersistenceException;
   public Integer getRowId() ;
   public void setRowId(Integer newRowId) throws PersistenceException;
}
