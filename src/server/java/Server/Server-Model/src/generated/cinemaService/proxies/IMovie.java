/**--- Generated at Wed Mar 08 17:23:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.Movie;
import generated.cinemaService.MovieScreening;
import java.util.Set;
import exceptions.ConstraintViolation;
public interface IMovie extends Identifiable{
   public Movie getTheObject();
   public Integer getId();
   public Set<MovieScreening> getScreenings() throws PersistenceException;
   public void addToScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException;
   public String getTitle() ;
   public void setTitle(String newTitle) throws PersistenceException;
   public String getDescription() ;
   public void setDescription(String newDescription) throws PersistenceException;
}
