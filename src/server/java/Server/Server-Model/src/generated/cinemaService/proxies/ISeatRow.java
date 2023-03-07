/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.SeatRow;
import generated.cinemaService.PriceCategory;
import generated.cinemaService.Seat;
import java.util.Set;
import generated.cinemaService.CinemaHall;
public interface ISeatRow extends Identifiable{
   public SeatRow getTheObject();
   public Integer getId();
   public PriceCategory getPrice() throws PersistenceException;
   public void setPrice(PriceCategory newPrice)throws PersistenceException;
   public Set<Seat> getSeats() throws PersistenceException;
   public void addToSeats(Seat arg) throws PersistenceException;
   public boolean removeFromSeats(Seat arg) throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public CinemaHall getHall() throws PersistenceException;
}
