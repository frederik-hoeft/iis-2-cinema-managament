/**--- Generated at Tue Mar 07 22:26:21 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.BookingState;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.cinemaService.Movie;
import generated.cinemaService.CinemaHall;
public interface IMovieScreening extends Identifiable{
   public MovieScreening getTheObject();
   public Integer getId();
   public Set<BookingState> getBookings() throws PersistenceException;
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException;
   public Boolean getFinished() ;
   public void setFinished(Boolean newFinished) throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public Movie getMovie() throws PersistenceException;
   public CinemaHall getHall() throws PersistenceException;
}
