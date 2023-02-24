/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
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
import generated.cinema.relationControl.MovieScreening_CinemaHallSupervisor;
import generated.cinema.proxies.ISeatRow;
import generated.cinema.relationControl.SeatRow_CinemaHallSupervisor;
import generated.cinema.relationControl.CinemaHall_SeatRowSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.CinemaHallProxy;
import observation.Observable;
import generated.cinema.proxies.ICinemaHall;
import generated.cinema.relationControl.*;
import generated.cinema.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class CinemaHall extends Observable implements java.io.Serializable, ICinemaHall
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Boolean available;
   private Integer roomId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private CinemaHall(Integer id, Boolean available, Integer roomId, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.available = available;
      this.roomId = roomId;
      if(objectOnly) return;
   }
   public static CinemaHall createFresh(Boolean available, Integer roomId)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("CinemaHall", "id, typeKey, available, roomId", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "CinemaHall").toString() + ", " + available.toString() + ", " + roomId.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      CinemaHall me = new CinemaHall(id, available, roomId, false);
      Cinema.getInstance().addCinemaHallProxy(new CinemaHallProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getCinemaHallCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      CinemaHall toBeDeleted = Cinema.getInstance().getCinemaHall(id);
      List<IMovieScreening> ownersInMovieScreening_CinemaHall = MovieScreening_CinemaHallSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInMovieScreening_CinemaHall.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association MovieScreening_CinemaHall");
      MovieScreening_CinemaHallSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<ISeatRow> ownersInSeatRow_CinemaHall = SeatRow_CinemaHallSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInSeatRow_CinemaHall.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association SeatRow_CinemaHall");
      SeatRow_CinemaHallSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      CinemaHall_SeatRowSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getCinemaHallCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("CinemaHall", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static CinemaHall instantiateRuntimeCopy(CinemaHallProxy proxy, Boolean available, Integer roomId){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new CinemaHall(proxy.getId(), available, roomId, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public CinemaHall getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ICinemaHall)) return false;
      return ((ICinemaHall)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Set<SeatRow> getRows() throws PersistenceException{
      Set<SeatRow> result = new HashSet<>();
      for (ISeatRow i : CinemaHall_SeatRowSupervisor.getInstance().getRows(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToRows(SeatRow arg) throws PersistenceException{
      CinemaHall_SeatRowSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromRows(SeatRow arg) throws PersistenceException{
      return CinemaHall_SeatRowSupervisor.getInstance().remove(this, arg);
   }
   public Boolean getAvailable() {
      return this.available;
   }
   public void setAvailable(Boolean newAvailable) throws PersistenceException{
      this.available = newAvailable;
      try{Cinema.getInstance().getDmlExecuter().update("CinemaHall", "available", newAvailable.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getRoomId() {
      return this.roomId;
   }
   public void setRoomId(Integer newRoomId) throws PersistenceException{
      this.roomId = newRoomId;
      try{Cinema.getInstance().getDmlExecuter().update("CinemaHall", "roomId", newRoomId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
