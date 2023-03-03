/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.SeatRow;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.PriceCategory;
import generated.cinemaService.Seat;
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
}
