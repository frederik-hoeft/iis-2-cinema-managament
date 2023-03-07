/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
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
import generated.cinemaService.relationControl.SeatRow_SeatSupervisor;
import java.util.List;
import generated.cinemaService.proxies.IBookingState;
import generated.cinemaService.relationControl.BookingState_SeatSupervisor;
import generated.cinemaService.relationControl.Seat_SeatRowSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.SeatProxy;
import observation.Observable;
import generated.cinemaService.proxies.ISeat;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Seat extends Observable implements java.io.Serializable, ISeat
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private String name;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Seat(Integer id, SeatRow row, String name, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      Seat_SeatRowSupervisor.getInstance().set(this, row);
      this.name = name;
      if(objectOnly) return;
   }
   public static Seat createFresh(SeatRow row, String name)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Seat", "id, typeKey, name", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "Seat").toString() + ", " + "'" + name + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Seat me = new Seat(id, row, name, false);
      CinemaService.getInstance().addSeatProxy(new SeatProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getSeatCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Seat toBeDeleted = CinemaService.getInstance().getSeat(id);
      SeatRow_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<IBookingState> ownersInBookingState_Seat = BookingState_SeatSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInBookingState_Seat.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association BookingState_Seat");
      BookingState_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Seat_SeatRowSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getSeatCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("Seat", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Seat instantiateRuntimeCopy(SeatProxy proxy, SeatRow row, String name)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Seat(proxy.getId(), row, name, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Seat getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISeat)) return false;
      return ((ISeat)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public SeatRow getRow() throws PersistenceException{
      return Seat_SeatRowSupervisor.getInstance().getRow(this).getTheObject();
   }
   public void setRow(SeatRow newRow)throws PersistenceException{
      Seat_SeatRowSupervisor.getInstance().change(this, this.getRow(), newRow);
   }
   public String getName() {
      return this.name;
   }
   public void setName(String newName) throws PersistenceException{
      this.name = newName;
      try{CinemaService.getInstance().getDmlExecuter().update("Seat", "name", "'" + newName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
