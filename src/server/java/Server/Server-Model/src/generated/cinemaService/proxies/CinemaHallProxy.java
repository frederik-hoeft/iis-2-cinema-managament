/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.CinemaHall;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.MovieScreening;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.cinemaService.SeatRow;
import java.util.List;
public class CinemaHallProxy implements ICinemaHall{
   private Integer id;
   private Optional<CinemaHall> theObject;
   public CinemaHallProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public CinemaHallProxy(CinemaHall theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public CinemaHall getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "CinemaHall";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ICinemaHall)) return false;
      return ((ICinemaHall)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private CinemaHall load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("CinemaHall", this.id);
         Boolean available = rs.get().getBoolean("available");
         String name = rs.get().getString("name");
         return CinemaHall.instantiateRuntimeCopy(this, available, name);
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
   public List<SeatRow> getRows() throws PersistenceException{
      return this.getTheObject().getRows();
   }
   public void addToRows(SeatRow arg) throws ConstraintViolation, PersistenceException{
      this.getTheObject().addToRows(arg);
   }
   public boolean removeFromRows(SeatRow arg) throws ConstraintViolation, PersistenceException{
      return this.getTheObject().removeFromRows(arg);
   }
   public Boolean getAvailable() {
      return this.getTheObject().getAvailable();
   }
   public void setAvailable(Boolean newAvailable) throws PersistenceException{
      this.getTheObject().setAvailable(newAvailable);
   }
   public String getName() {
      return this.getTheObject().getName();
   }
   public void setName(String newName) throws PersistenceException{
      this.getTheObject().setName(newName);
   }
}
