/**--- Generated at Tue Mar 07 13:29:05 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.Movie;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.MovieScreening;
import java.util.Set;
import exceptions.ConstraintViolation;
public class MovieProxy implements IMovie{
   private Integer id;
   private Optional<Movie> theObject;
   public MovieProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public MovieProxy(Movie theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Movie getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Movie";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IMovie)) return false;
      return ((IMovie)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Movie load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Movie", this.id);
         String title = rs.get().getString("title");
         String description = rs.get().getString("description");
         return Movie.instantiateRuntimeCopy(this, title, description);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<MovieScreening> getScreenings() throws PersistenceException{
      return this.getTheObject().getScreenings();
   }
   public void addToScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException{
      this.getTheObject().addToScreenings(arg);
   }
   public boolean removeFromScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException{
      return this.getTheObject().removeFromScreenings(arg);
   }
   public String getTitle() {
      return this.getTheObject().getTitle();
   }
   public void setTitle(String newTitle) throws PersistenceException{
      this.getTheObject().setTitle(newTitle);
   }
   public String getDescription() {
      return this.getTheObject().getDescription();
   }
   public void setDescription(String newDescription) throws PersistenceException{
      this.getTheObject().setDescription(newDescription);
   }
}
