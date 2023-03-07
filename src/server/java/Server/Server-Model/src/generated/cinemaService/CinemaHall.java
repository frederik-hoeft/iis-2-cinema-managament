/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
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
import generated.cinemaService.relationControl.CinemaHall_MovieScreeningSupervisor;
import generated.cinemaService.proxies.ISeatRow;
import generated.cinemaService.relationControl.CinemaHall_SeatRowSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.CinemaHallProxy;
import observation.Observable;
import generated.cinemaService.proxies.ICinemaHall;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class CinemaHall extends Observable implements java.io.Serializable, ICinemaHall
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Boolean available;
   private String name;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private CinemaHall(Integer id, Boolean available, String name, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.available = available;
      this.name = name;
      if(objectOnly) return;
   }
   public static CinemaHall createFresh(Boolean available, String name)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("CinemaHall", "id, typeKey, available, name", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "CinemaHall").toString() + ", " + available.toString() + ", " + "'" + name + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      CinemaHall me = new CinemaHall(id, available, name, false);
      CinemaService.getInstance().addCinemaHallProxy(new CinemaHallProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getCinemaHallCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      CinemaHall toBeDeleted = CinemaService.getInstance().getCinemaHall(id);
      List<IMovieScreening> targetsInCinemaHall_MovieScreening = CinemaHall_MovieScreeningSupervisor.getInstance().getRelationData().getRelatedTargets(toBeDeleted);
      if(targetsInCinemaHall_MovieScreening.size()>0) throw new ConstraintViolation(" Deletion not possible: Object still contains other objects in Association CinemaHall_MovieScreening");
      CinemaHall_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      List<ISeatRow> targetsInCinemaHall_SeatRow = CinemaHall_SeatRowSupervisor.getInstance().getRelationData().getRelatedTargets(toBeDeleted);
      if(targetsInCinemaHall_SeatRow.size()>0) throw new ConstraintViolation(" Deletion not possible: Object still contains other objects in Association CinemaHall_SeatRow");
      CinemaHall_SeatRowSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getCinemaHallCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("CinemaHall", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static CinemaHall instantiateRuntimeCopy(CinemaHallProxy proxy, Boolean available, String name){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new CinemaHall(proxy.getId(), available, name, true);
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
   public Set<MovieScreening> getScreenings() throws PersistenceException{
      Set<MovieScreening> result = new HashSet<>();
      for (IMovieScreening i : CinemaHall_MovieScreeningSupervisor.getInstance().getScreenings(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException{
      CinemaHall_MovieScreeningSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromScreenings(MovieScreening arg) throws ConstraintViolation, PersistenceException{
      return CinemaHall_MovieScreeningSupervisor.getInstance().remove(this, arg);
   }
   public List<SeatRow> getRows() throws PersistenceException{
      List<SeatRow> result = new ArrayList<>();
      for (ISeatRow i : CinemaHall_SeatRowSupervisor.getInstance().getRows(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToRows(SeatRow arg) throws ConstraintViolation, PersistenceException{
      CinemaHall_SeatRowSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromRows(SeatRow arg) throws ConstraintViolation, PersistenceException{
      return CinemaHall_SeatRowSupervisor.getInstance().remove(this, arg);
   }
   public Boolean getAvailable() {
      return this.available;
   }
   public void setAvailable(Boolean newAvailable) throws PersistenceException{
      this.available = newAvailable;
      try{CinemaService.getInstance().getDmlExecuter().update("CinemaHall", "available", newAvailable.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getName() {
      return this.name;
   }
   public void setName(String newName) throws PersistenceException{
      this.name = newName;
      try{CinemaService.getInstance().getDmlExecuter().update("CinemaHall", "name", "'" + newName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
