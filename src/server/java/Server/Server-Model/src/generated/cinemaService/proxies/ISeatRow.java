/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.SeatRow;
import generated.cinemaService.PriceCategory;
import generated.cinemaService.Seat;
import java.util.List;
import exceptions.ConstraintViolation;
import generated.cinemaService.CinemaHall;
public interface ISeatRow extends Identifiable{
   public SeatRow getTheObject();
   public Integer getId();
   public PriceCategory getPrice() throws PersistenceException;
   public void setPrice(PriceCategory newPrice)throws PersistenceException;
   public List<Seat> getSeats() throws PersistenceException;
   public void addToSeats(Seat arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromSeats(Seat arg) throws ConstraintViolation, PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public CinemaHall getHall() throws PersistenceException;
}
