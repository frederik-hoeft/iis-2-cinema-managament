/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- Mode = No Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinema;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import exceptions.ConstraintViolation;
import java.util.List;
import generated.cinema.proxies.IMovieScreening;
import generated.cinema.relationControl.MovieScreeninig_MovieSupervisor;
import generated.cinema.relationControl.Movie_MovieScreeningSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.MovieProxy;
import observation.Observable;
import generated.cinema.proxies.IMovie;
import generated.cinema.relationControl.*;
import generated.cinema.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Movie extends Observable implements java.io.Serializable, IMovie
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer movieId;
   private String title;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Movie(Integer id, Integer movieId, String title, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.movieId = movieId;
      this.title = title;
      if(objectOnly) return;
   }
   public static Movie createFresh(Integer movieId, String title)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Movie", "id, typeKey, movieId, title", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "Movie").toString() + ", " + movieId.toString() + ", " + "'" + title + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Movie me = new Movie(id, movieId, title, false);
      Cinema.getInstance().addMovieProxy(new MovieProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getMovieCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Movie toBeDeleted = Cinema.getInstance().getMovie(id);
      List<IMovieScreening> ownersInMovieScreeninig_Movie = MovieScreeninig_MovieSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInMovieScreeninig_Movie.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association MovieScreeninig_Movie");
      MovieScreeninig_MovieSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Movie_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getMovieCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("Movie", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Movie instantiateRuntimeCopy(MovieProxy proxy, Integer movieId, String title){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Movie(proxy.getId(), movieId, title, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Movie getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IMovie)) return false;
      return ((IMovie)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Set<MovieScreening> getScreenings() throws PersistenceException{
      Set<MovieScreening> result = new HashSet<>();
      for (IMovieScreening i : Movie_MovieScreeningSupervisor.getInstance().getScreenings(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToScreenings(MovieScreening arg) throws PersistenceException{
      Movie_MovieScreeningSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromScreenings(MovieScreening arg) throws PersistenceException{
      return Movie_MovieScreeningSupervisor.getInstance().remove(this, arg);
   }
   public Integer getMovieId() {
      return this.movieId;
   }
   public void setMovieId(Integer newMovieId) throws PersistenceException{
      this.movieId = newMovieId;
      try{Cinema.getInstance().getDmlExecuter().update("Movie", "movieId", newMovieId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getTitle() {
      return this.title;
   }
   public void setTitle(String newTitle) throws PersistenceException{
      this.title = newTitle;
      try{Cinema.getInstance().getDmlExecuter().update("Movie", "title", "'" + newTitle + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
