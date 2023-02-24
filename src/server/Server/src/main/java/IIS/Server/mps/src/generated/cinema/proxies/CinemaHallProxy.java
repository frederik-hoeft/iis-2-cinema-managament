/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.CinemaHall;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.SeatRow;
import java.util.Set;
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
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("CinemaHall", this.id);
         Boolean available = rs.get().getBoolean("available");
         Integer roomId = rs.get().getInt("roomId");
         return CinemaHall.instantiateRuntimeCopy(this, available, roomId);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<SeatRow> getRows() throws PersistenceException{
      return this.getTheObject().getRows();
   }
   public void addToRows(SeatRow arg) throws PersistenceException{
      this.getTheObject().addToRows(arg);
   }
   public boolean removeFromRows(SeatRow arg) throws PersistenceException{
      return this.getTheObject().removeFromRows(arg);
   }
   public Boolean getAvailable() {
      return this.getTheObject().getAvailable();
   }
   public void setAvailable(Boolean newAvailable) throws PersistenceException{
      this.getTheObject().setAvailable(newAvailable);
   }
   public Integer getRoomId() {
      return this.getTheObject().getRoomId();
   }
   public void setRoomId(Integer newRoomId) throws PersistenceException{
      this.getTheObject().setRoomId(newRoomId);
   }
}
