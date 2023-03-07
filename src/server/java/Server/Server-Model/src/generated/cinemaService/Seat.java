/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
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
import generated.cinemaService.relationControl.Seat_BookingStateSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.SeatProxy;
import observation.Observable;
import generated.cinemaService.proxies.ISeat;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Seat extends Observable implements java.io.Serializable, ISeat
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private String name;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Seat(Integer id, String name, SeatRow row, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      this.name = name;
      if(objectOnly) return;
      try{SeatRow_SeatSupervisor.getInstance().add(row,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
   }
   public static Seat createFresh(String name, SeatRow row)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Seat", "id, typeKey, name", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "Seat").toString() + ", " + "'" + name + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Seat me = new Seat(id, name, row, false);
      CinemaService.getInstance().addSeatProxy(new SeatProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getSeatCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Seat toBeDeleted = CinemaService.getInstance().getSeat(id);
      SeatRow_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<IBookingState> targetsInSeat_BookingState = Seat_BookingStateSupervisor.getInstance().getRelationData().getRelatedTargets(toBeDeleted);
      if(targetsInSeat_BookingState.size()>0) throw new ConstraintViolation(" Deletion not possible: Object still contains other objects in Association Seat_BookingState");
      Seat_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getSeatCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("Seat", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Seat instantiateRuntimeCopy(SeatProxy proxy, String name, SeatRow row)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Seat(proxy.getId(), name, row, true);
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
   public Set<BookingState> getBookings() throws PersistenceException{
      Set<BookingState> result = new HashSet<>();
      for (IBookingState i : Seat_BookingStateSupervisor.getInstance().getBookings(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      Seat_BookingStateSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      return Seat_BookingStateSupervisor.getInstance().remove(this, arg);
   }
   public String getName() {
      return this.name;
   }
   public void setName(String newName) throws PersistenceException{
      this.name = newName;
      try{CinemaService.getInstance().getDmlExecuter().update("Seat", "name", "'" + newName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public SeatRow getRow() throws PersistenceException{
      return SeatRow_SeatSupervisor.getInstance().getRow(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
