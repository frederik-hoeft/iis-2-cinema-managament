/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.MovieScreening;
import generated.cinema.Movie;
import generated.cinema.BookingState;
import java.util.Set;
import generated.cinema.CinemaHall;
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
   public Integer getMovieScreeningId() ;
   public void setMovieScreeningId(Integer newMovieScreeningId) throws PersistenceException;
   public Boolean getFinished() ;
   public void setFinished(Boolean newFinished) throws PersistenceException;
}
