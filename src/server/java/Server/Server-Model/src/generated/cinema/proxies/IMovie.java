/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.Movie;
import generated.cinema.MovieScreening;
import java.util.Set;
public interface IMovie extends Identifiable{
   public Movie getTheObject();
   public Integer getId();
   public Set<MovieScreening> getScreenings() throws PersistenceException;
   public void addToScreenings(MovieScreening arg) throws PersistenceException;
   public boolean removeFromScreenings(MovieScreening arg) throws PersistenceException;
   public Integer getMovieId() ;
   public void setMovieId(Integer newMovieId) throws PersistenceException;
   public String getTitle() ;
   public void setTitle(String newTitle) throws PersistenceException;
}
