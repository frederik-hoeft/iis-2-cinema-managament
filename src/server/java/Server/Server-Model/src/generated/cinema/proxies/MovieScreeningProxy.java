/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.MovieScreening;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.Movie;
import generated.cinema.relationControl.MovieScreeninig_MovieSupervisor;
import generated.cinema.BookingState;
import java.util.Set;
import generated.cinema.CinemaHall;
import generated.cinema.relationControl.MovieScreening_CinemaHallSupervisor;
public class MovieScreeningProxy implements IMovieScreening{
   private Integer id;
   private Optional<MovieScreening> theObject;
   public MovieScreeningProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public MovieScreeningProxy(MovieScreening theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public MovieScreening getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "MovieScreening";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IMovieScreening)) return false;
      return ((IMovieScreening)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private MovieScreening load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("MovieScreening", this.id);
         Movie movie = MovieScreeninig_MovieSupervisor.getInstance().getMovie(this).getTheObject();
         CinemaHall hall = MovieScreening_CinemaHallSupervisor.getInstance().getHall(this).getTheObject();
         Integer movieScreeningId = rs.get().getInt("movieScreeningId");
         Boolean finished = rs.get().getBoolean("finished");
         return MovieScreening.instantiateRuntimeCopy(this, movie, hall, movieScreeningId, finished);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Movie getMovie() throws PersistenceException{
      return this.getTheObject().getMovie();
   }
   public void setMovie(Movie newMovie)throws PersistenceException{
      this.getTheObject().setMovie(newMovie);
   }
   public Set<BookingState> getBookingStates() throws PersistenceException{
      return this.getTheObject().getBookingStates();
   }
   public void addToBookingStates(BookingState arg) throws PersistenceException{
      this.getTheObject().addToBookingStates(arg);
   }
   public boolean removeFromBookingStates(BookingState arg) throws PersistenceException{
      return this.getTheObject().removeFromBookingStates(arg);
   }
   public CinemaHall getHall() throws PersistenceException{
      return this.getTheObject().getHall();
   }
   public void setHall(CinemaHall newHall)throws PersistenceException{
      this.getTheObject().setHall(newHall);
   }
   public Integer getMovieScreeningId() {
      return this.getTheObject().getMovieScreeningId();
   }
   public void setMovieScreeningId(Integer newMovieScreeningId) throws PersistenceException{
      this.getTheObject().setMovieScreeningId(newMovieScreeningId);
   }
   public Boolean getFinished() {
      return this.getTheObject().getFinished();
   }
   public void setFinished(Boolean newFinished) throws PersistenceException{
      this.getTheObject().setFinished(newFinished);
   }
}
