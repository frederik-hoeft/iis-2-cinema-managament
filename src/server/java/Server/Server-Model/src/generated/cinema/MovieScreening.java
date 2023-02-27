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
import generated.cinema.relationControl.Movie_MovieScreeningSupervisor;
import java.util.List;
import generated.cinema.proxies.IBookingState;
import generated.cinema.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinema.relationControl.MovieScreeninig_MovieSupervisor;
import generated.cinema.relationControl.MovieScreening_BookingStateSupervisor;
import generated.cinema.relationControl.MovieScreening_CinemaHallSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.MovieScreeningProxy;
import observation.Observable;
import generated.cinema.proxies.IMovieScreening;
import generated.cinema.relationControl.*;
import generated.cinema.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class MovieScreening extends Observable implements java.io.Serializable, IMovieScreening
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer movieScreeningId;
   private Boolean finished;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private MovieScreening(Integer id, Movie movie, CinemaHall hall, Integer movieScreeningId, Boolean finished, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      MovieScreeninig_MovieSupervisor.getInstance().set(this, movie);
      MovieScreening_CinemaHallSupervisor.getInstance().set(this, hall);
      this.movieScreeningId = movieScreeningId;
      this.finished = finished;
      if(objectOnly) return;
   }
   public static MovieScreening createFresh(Movie movie, CinemaHall hall, Integer movieScreeningId, Boolean finished)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("MovieScreening", "id, typeKey, movieScreeningId, finished", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "MovieScreening").toString() + ", " + movieScreeningId.toString() + ", " + finished.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      MovieScreening me = new MovieScreening(id, movie, hall, movieScreeningId, finished, false);
      Cinema.getInstance().addMovieScreeningProxy(new MovieScreeningProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getMovieScreeningCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      MovieScreening toBeDeleted = Cinema.getInstance().getMovieScreening(id);
      Movie_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<IBookingState> ownersInBookingState_MovieScreening = BookingState_MovieScreeningSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInBookingState_MovieScreening.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association BookingState_MovieScreening");
      BookingState_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      MovieScreeninig_MovieSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      MovieScreening_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      MovieScreening_CinemaHallSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getMovieScreeningCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("MovieScreening", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static MovieScreening instantiateRuntimeCopy(MovieScreeningProxy proxy, Movie movie, CinemaHall hall, Integer movieScreeningId, Boolean finished)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new MovieScreening(proxy.getId(), movie, hall, movieScreeningId, finished, true);
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
   public Integer getMovieScreeningId() {
      return this.movieScreeningId;
   }
   public void setMovieScreeningId(Integer newMovieScreeningId) throws PersistenceException{
      this.movieScreeningId = newMovieScreeningId;
      try{Cinema.getInstance().getDmlExecuter().update("MovieScreening", "movieScreeningId", newMovieScreeningId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Boolean getFinished() {
      return this.finished;
   }
   public void setFinished(Boolean newFinished) throws PersistenceException{
      this.finished = newFinished;
      try{Cinema.getInstance().getDmlExecuter().update("MovieScreening", "finished", newFinished.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
