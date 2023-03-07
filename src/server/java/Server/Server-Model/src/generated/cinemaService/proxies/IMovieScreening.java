/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.BookingState;
import java.util.Set;
import generated.cinemaService.Movie;
import generated.cinemaService.CinemaHall;
import java.util.List;
public interface IMovieScreening extends Identifiable{
   public MovieScreening getTheObject();
   public Integer getId();
   public Set<BookingState> getBookingStates() throws PersistenceException;
   public void addToBookingStates(BookingState arg) throws PersistenceException;
   public boolean removeFromBookingStates(BookingState arg) throws PersistenceException;
   public Boolean getFinished() ;
   public void setFinished(Boolean newFinished) throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public Movie getMovie() throws PersistenceException;
   public List<CinemaHall> getHall() throws PersistenceException;
}
