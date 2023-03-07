/**--- Generated at Wed Mar 08 00:30:17 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinemaService;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import exceptions.ConstraintViolation;
import java.util.List;
import generated.cinemaService.proxies.IMovieScreening;
import generated.cinemaService.relationControl.Movie_MovieScreeningSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.MovieProxy;
import observation.Observable;
import generated.cinemaService.proxies.IMovie;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Movie extends Observable implements java.io.Serializable, IMovie
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private String title;
   private String description;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Movie(Integer id, String title, String description, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.title = title;
      this.description = description;
      if(objectOnly) return;
   }
   public static Movie createFresh(String title, String description)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Movie", "id, typeKey, title, description", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "Movie").toString() + ", " + "'" + title + "'" + ", " + "'" + description + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Movie me = new Movie(id, title, description, false);
      CinemaService.getInstance().addMovieProxy(new MovieProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getMovieCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Movie toBeDeleted = CinemaService.getInstance().getMovie(id);
      List<IMovieScreening> targetsInMovie_MovieScreening = Movie_MovieScreeningSupervisor.getInstance().getRelationData().getRelatedTargets(toBeDeleted);
      if(targetsInMovie_MovieScreening.size()>0) throw new ConstraintViolation(" Deletion not possible: Object still contains other objects in Association Movie_MovieScreening");
      Movie_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getMovieCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("Movie", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Movie instantiateRuntimeCopy(MovieProxy proxy, String title, String description){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Movie(proxy.getId(), title, description, true);
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
   public void addToScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException{
      Movie_MovieScreeningSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException{
      return Movie_MovieScreeningSupervisor.getInstance().remove(this, arg);
   }
   public String getTitle() {
      return this.title;
   }
   public void setTitle(String newTitle) throws PersistenceException{
      this.title = newTitle;
      try{CinemaService.getInstance().getDmlExecuter().update("Movie", "title", "'" + newTitle + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getDescription() {
      return this.description;
   }
   public void setDescription(String newDescription) throws PersistenceException{
      this.description = newDescription;
      try{CinemaService.getInstance().getDmlExecuter().update("Movie", "description", "'" + newDescription + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
