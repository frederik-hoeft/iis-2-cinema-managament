/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
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
import java.util.List;
import generated.cinemaService.proxies.IBookingState;
import generated.cinemaService.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinemaService.relationControl.MovieScreeninig_MovieSupervisor;
import generated.cinemaService.relationControl.MovieScreening_BookingStateSupervisor;
import generated.cinemaService.relationControl.MovieScreening_CinemaHallSupervisor;
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
   private String Name;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private MovieScreening(Integer id, Movie movie, CinemaHall hall, Boolean finished, String Name, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      MovieScreeninig_MovieSupervisor.getInstance().set(this, movie);
      MovieScreening_CinemaHallSupervisor.getInstance().set(this, hall);
      this.finished = finished;
      this.Name = Name;
      if(objectOnly) return;
   }
   public static MovieScreening createFresh(Movie movie, CinemaHall hall, Boolean finished, String Name)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("MovieScreening", "id, typeKey, finished, Name", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "MovieScreening").toString() + ", " + finished.toString() + ", " + "'" + Name + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      MovieScreening me = new MovieScreening(id, movie, hall, finished, Name, false);
      CinemaService.getInstance().addMovieScreeningProxy(new MovieScreeningProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getMovieScreeningCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      MovieScreening toBeDeleted = CinemaService.getInstance().getMovieScreening(id);
      Movie_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<IBookingState> ownersInBookingState_MovieScreening = BookingState_MovieScreeningSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInBookingState_MovieScreening.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association BookingState_MovieScreening");
      BookingState_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      MovieScreeninig_MovieSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      MovieScreening_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      MovieScreening_CinemaHallSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getMovieScreeningCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("MovieScreening", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static MovieScreening instantiateRuntimeCopy(MovieScreeningProxy proxy, Movie movie, CinemaHall hall, Boolean finished, String Name)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new MovieScreening(proxy.getId(), movie, hall, finished, Name, true);
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
   public Movie getMovie() throws PersistenceException{
      return MovieScreeninig_MovieSupervisor.getInstance().getMovie(this).getTheObject();
   }
   public void setMovie(Movie newMovie)throws PersistenceException{
      MovieScreeninig_MovieSupervisor.getInstance().change(this, this.getMovie(), newMovie);
   }
   public Set<BookingState> getBookingStates() throws PersistenceException{
      Set<BookingState> result = new HashSet<>();
      for (IBookingState i : MovieScreening_BookingStateSupervisor.getInstance().getBookingStates(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToBookingStates(BookingState arg) throws PersistenceException{
      MovieScreening_BookingStateSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBookingStates(BookingState arg) throws PersistenceException{
      return MovieScreening_BookingStateSupervisor.getInstance().remove(this, arg);
   }
   public CinemaHall getHall() throws PersistenceException{
      return MovieScreening_CinemaHallSupervisor.getInstance().getHall(this).getTheObject();
   }
   public void setHall(CinemaHall newHall)throws PersistenceException{
      MovieScreening_CinemaHallSupervisor.getInstance().change(this, this.getHall(), newHall);
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
      return this.Name;
   }
   public void setName(String newName) throws PersistenceException{
      this.Name = newName;
      try{CinemaService.getInstance().getDmlExecuter().update("MovieScreening", "Name", "'" + newName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
