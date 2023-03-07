/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.MovieScreening;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.cinemaService.SeatRow;
public interface ICinemaHall extends Identifiable{
   public CinemaHall getTheObject();
   public Integer getId();
   public Set<MovieScreening> getScreenings() throws PersistenceException;
   public void addToScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException;
   public Set<SeatRow> getRows() throws PersistenceException;
   public void addToRows(SeatRow arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromRows(SeatRow arg) throws ConstraintViolation, PersistenceException;
   public Boolean getAvailable() ;
   public void setAvailable(Boolean newAvailable) throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
}
