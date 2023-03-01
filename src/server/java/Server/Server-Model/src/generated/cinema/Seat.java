/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- Mode = Integrated Database 
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
import generated.cinema.relationControl.SeatRow_SeatSupervisor;
import java.util.List;
import generated.cinema.proxies.IBookingState;
import generated.cinema.relationControl.BookingState_SeatSupervisor;
import generated.cinema.relationControl.Seat_SeatRowSupervisor;
import generated.cinema.relationControl.Seat_BookingStateSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.SeatProxy;
import observation.Observable;
import generated.cinema.proxies.ISeat;
import generated.cinema.relationControl.*;
import generated.cinema.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Optional;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Seat extends Observable implements java.io.Serializable, ISeat
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer seatId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Seat(Integer id, SeatRow row, Integer seatId, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      Seat_SeatRowSupervisor.getInstance().set(this, row);
      this.seatId = seatId;
      if(objectOnly) return;
   }
   public static Seat createFresh(SeatRow row, Integer seatId)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Seat", "id, typeKey, seatId", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "Seat").toString() + ", " + seatId.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Seat me = new Seat(id, row, seatId, false);
      Cinema.getInstance().addSeatProxy(new SeatProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getSeatCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Seat toBeDeleted = Cinema.getInstance().getSeat(id);
      SeatRow_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<IBookingState> ownersInBookingState_Seat = BookingState_SeatSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInBookingState_Seat.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association BookingState_Seat");
      BookingState_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Seat_SeatRowSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Seat_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getSeatCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("Seat", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Seat instantiateRuntimeCopy(SeatProxy proxy, SeatRow row, Integer seatId)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Seat(proxy.getId(), row, seatId, true);
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
   public Optional<BookingState> getBookingState() throws PersistenceException{
      Optional<IBookingState> opt = Seat_BookingStateSupervisor.getInstance().getBookingState(this);
      return opt.isPresent() ? Optional.of(Seat_BookingStateSupervisor.getInstance().getBookingState(this).get().getTheObject()) : Optional.empty();
   }
   public void setBookingState(BookingState newBookingState)throws PersistenceException{
      if(this.getBookingState().isPresent()) Seat_BookingStateSupervisor.getInstance().change(this, this.getBookingState().get(), newBookingState); else Seat_BookingStateSupervisor.getInstance().set(this, newBookingState);
   }
   public Integer getSeatId() {
      return this.seatId;
   }
   public void setSeatId(Integer newSeatId) throws PersistenceException{
      this.seatId = newSeatId;
      try{Cinema.getInstance().getDmlExecuter().update("Seat", "seatId", newSeatId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
