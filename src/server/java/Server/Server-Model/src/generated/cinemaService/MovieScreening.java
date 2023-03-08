/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
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
import generated.cinemaService.relationControl.Movie_MovieScreeningSupervisor;
import generated.cinemaService.relationControl.CinemaHall_MovieScreeningSupervisor;
import java.util.List;
import generated.cinemaService.proxies.IBookingState;
import generated.cinemaService.relationControl.MovieScreening_BookingStateSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.MovieScreeningProxy;
import observation.Observable;
import generated.cinemaService.proxies.IMovieScreening;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class MovieScreening extends Observable implements java.io.Serializable, IMovieScreening
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Boolean finished;
   private String name;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private MovieScreening(Integer id, Boolean finished, String name, Movie movie, CinemaHall hall, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      this.finished = finished;
      this.name = name;
      if(objectOnly) return;
      try{Movie_MovieScreeningSupervisor.getInstance().add(movie,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
      try{CinemaHall_MovieScreeningSupervisor.getInstance().add(hall,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
   }
   public static MovieScreening createFresh(Boolean finished, String name, Movie movie, CinemaHall hall)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("MovieScreening", "id, typeKey, finished, name", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "MovieScreening").toString() + ", " + finished.toString() + ", " + "'" + name + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      MovieScreening me = new MovieScreening(id, finished, name, movie, hall, false);
      CinemaService.getInstance().addMovieScreeningProxy(new MovieScreeningProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getMovieScreeningCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      MovieScreening toBeDeleted = CinemaService.getInstance().getMovieScreening(id);
      Movie_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      CinemaHall_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<IBookingState> targetsInMovieScreening_BookingState = MovieScreening_BookingStateSupervisor.getInstance().getRelationData().getRelatedTargets(toBeDeleted);
      if(targetsInMovieScreening_BookingState.size()>0) throw new ConstraintViolation(" Deletion not possible: Object still contains other objects in Association MovieScreening_BookingState");
      MovieScreening_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getMovieScreeningCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("MovieScreening", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static MovieScreening instantiateRuntimeCopy(MovieScreeningProxy proxy, Boolean finished, String name, Movie movie, CinemaHall hall)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new MovieScreening(proxy.getId(), finished, name, movie, hall, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public MovieScreening getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IMovieScreening)) return false;
      return ((IMovieScreening)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Set<BookingState> getBookings() throws PersistenceException{
      Set<BookingState> result = new HashSet<>();
      for (IBookingState i : MovieScreening_BookingStateSupervisor.getInstance().getBookings(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      MovieScreening_BookingStateSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      return MovieScreening_BookingStateSupervisor.getInstance().remove(this, arg);
   }
   public Boolean getFinished() {
      return this.finished;
   }
   public void setFinished(Boolean newFinished) throws PersistenceException{
      this.finished = newFinished;
      try{CinemaService.getInstance().getDmlExecuter().update("MovieScreening", "finished", newFinished.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getName() {
      return this.name;
   }
   public void setName(String newName) throws PersistenceException{
      this.name = newName;
      try{CinemaService.getInstance().getDmlExecuter().update("MovieScreening", "name", "'" + newName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Movie getMovie() throws PersistenceException{
      return Movie_MovieScreeningSupervisor.getInstance().getMovie(this).getTheObject();
   }
   public CinemaHall getHall() throws PersistenceException{
      return CinemaHall_MovieScreeningSupervisor.getInstance().getHall(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
