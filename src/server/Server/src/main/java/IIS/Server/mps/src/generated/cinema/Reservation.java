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
import generated.cinema.relationControl.MovieScreening_BookingStateSupervisor;
import generated.cinema.relationControl.Seat_BookingStateSupervisor;
import generated.cinema.relationControl.Customer_BookingStateSupervisor;
import generated.cinema.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinema.relationControl.BookingState_SeatSupervisor;
import generated.cinema.relationControl.BookingState_CustomerSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.ReservationProxy;
import generated.cinema.proxies.IReservation;
import src.db.executer.PersistenceException;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Reservation extends BookingState implements java.io.Serializable, IReservation
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer reservationId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Reservation(Integer id, MovieScreening screening, Seat seat, Customer customer, Integer reservationId, boolean objectOnly)
   throws ConstraintViolation, PersistenceException{
      super(id, screening, seat, customer, objectOnly);
      this.reservationId = reservationId;
      if(objectOnly) return;
   }
   public static Reservation createFresh(MovieScreening screening, Seat seat, Customer customer, Integer reservationId)throws ConstraintViolation, PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("BookingState", "id, typeKey, reservationId", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "Reservation").toString() + ", " + reservationId.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Reservation me = new Reservation(id, screening, seat, customer, reservationId, false);
      Cinema.getInstance().addReservationProxy(new ReservationProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getReservationCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Reservation toBeDeleted = Cinema.getInstance().getReservation(id);
      MovieScreening_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Seat_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Customer_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      BookingState_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      BookingState_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      BookingState_CustomerSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getReservationCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("BookingState", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Reservation instantiateRuntimeCopy(ReservationProxy proxy, MovieScreening screening, Seat seat, Customer customer, Integer reservationId)throws ConstraintViolation, PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Reservation(proxy.getId(), screening, seat, customer, reservationId, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Reservation getTheObject(){
      return this;
   }
   public Integer getReservationId() {
      return this.reservationId;
   }
   public void setReservationId(Integer newReservationId) throws PersistenceException{
      this.reservationId = newReservationId;
      try{Cinema.getInstance().getDmlExecuter().update("BookingState", "reservationId", newReservationId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
