/**--- Generated at Wed Apr 05 19:50:24 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.MovieScreening;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.BookingState;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.cinemaService.Movie;
import generated.cinemaService.relationControl.Movie_MovieScreeningSupervisor;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.relationControl.CinemaHall_MovieScreeningSupervisor;
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
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("MovieScreening", this.id);
         Boolean finished = rs.get().getBoolean("finished");
         String name = rs.get().getString("name");
         Movie movie = Movie_MovieScreeningSupervisor.getInstance().getMovie(this).getTheObject();
         CinemaHall hall = CinemaHall_MovieScreeningSupervisor.getInstance().getHall(this).getTheObject();
         return MovieScreening.instantiateRuntimeCopy(this, finished, name, movie, hall);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<BookingState> getBookings() throws PersistenceException{
      return this.getTheObject().getBookings();
   }
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      this.getTheObject().addToBookings(arg);
   }
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      return this.getTheObject().removeFromBookings(arg);
   }
   public Boolean getFinished() {
      return this.getTheObject().getFinished();
   }
   public void setFinished(Boolean newFinished) throws PersistenceException{
      this.getTheObject().setFinished(newFinished);
   }
   public String getName() {
      return this.getTheObject().getName();
   }
   public void setName(String newName) throws PersistenceException{
      this.getTheObject().setName(newName);
   }
   public Movie getMovie() throws PersistenceException{
      return this.getTheObject().getMovie();
   }
   public CinemaHall getHall() throws PersistenceException{
      return this.getTheObject().getHall();
   }
}
