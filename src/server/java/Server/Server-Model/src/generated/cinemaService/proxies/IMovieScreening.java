/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Movie;
import generated.cinemaService.BookingState;
import java.util.Set;
import generated.cinemaService.CinemaHall;
public interface IMovieScreening extends Identifiable{
   public MovieScreening getTheObject();
   public Integer getId();
   public Movie getMovie() throws PersistenceException;
   public void setMovie(Movie newMovie)throws PersistenceException;
   public Set<BookingState> getBookingStates() throws PersistenceException;
   public void addToBookingStates(BookingState arg) throws PersistenceException;
   public boolean removeFromBookingStates(BookingState arg) throws PersistenceException;
   public CinemaHall getHall() throws PersistenceException;
   public void setHall(CinemaHall newHall)throws PersistenceException;
   public Boolean getFinished() ;
   public void setFinished(Boolean newFinished) throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
}
