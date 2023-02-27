/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.Movie;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.MovieScreening;
import java.util.Set;
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
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Movie", this.id);
         Integer movieId = rs.get().getInt("movieId");
         String title = rs.get().getString("title");
         return Movie.instantiateRuntimeCopy(this, movieId, title);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<MovieScreening> getScreenings() throws PersistenceException{
      return this.getTheObject().getScreenings();
   }
   public void addToScreenings(MovieScreening arg) throws PersistenceException{
      this.getTheObject().addToScreenings(arg);
   }
   public boolean removeFromScreenings(MovieScreening arg) throws PersistenceException{
      return this.getTheObject().removeFromScreenings(arg);
   }
   public Integer getMovieId() {
      return this.getTheObject().getMovieId();
   }
   public void setMovieId(Integer newMovieId) throws PersistenceException{
      this.getTheObject().setMovieId(newMovieId);
   }
   public String getTitle() {
      return this.getTheObject().getTitle();
   }
   public void setTitle(String newTitle) throws PersistenceException{
      this.getTheObject().setTitle(newTitle);
   }
}
