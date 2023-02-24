/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
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
import generated.cinema.proxies.BookingProxy;
import generated.cinema.proxies.IBooking;
import src.db.executer.PersistenceException;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Booking extends BookingState implements java.io.Serializable, IBooking
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer bookingId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Booking(Integer id, MovieScreening screening, Seat seat, Customer customer, Integer bookingId, boolean objectOnly)
   throws ConstraintViolation, PersistenceException{
      super(id, screening, seat, customer, objectOnly);
      this.bookingId = bookingId;
      if(objectOnly) return;
   }
   public static Booking createFresh(MovieScreening screening, Seat seat, Customer customer, Integer bookingId)throws ConstraintViolation, PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("BookingState", "id, typeKey, bookingId", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "Booking").toString() + ", " + bookingId.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Booking me = new Booking(id, screening, seat, customer, bookingId, false);
      Cinema.getInstance().addBookingProxy(new BookingProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getBookingCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Booking toBeDeleted = Cinema.getInstance().getBooking(id);
      MovieScreening_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Seat_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Customer_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      BookingState_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      BookingState_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      BookingState_CustomerSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getBookingCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("BookingState", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Booking instantiateRuntimeCopy(BookingProxy proxy, MovieScreening screening, Seat seat, Customer customer, Integer bookingId)throws ConstraintViolation, PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Booking(proxy.getId(), screening, seat, customer, bookingId, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Booking getTheObject(){
      return this;
   }
   public Integer getBookingId() {
      return this.bookingId;
   }
   public void setBookingId(Integer newBookingId) throws PersistenceException{
      this.bookingId = newBookingId;
      try{Cinema.getInstance().getDmlExecuter().update("BookingState", "bookingId", newBookingId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
