/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
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
import generated.cinemaService.relationControl.MovieScreening_BookingStateSupervisor;
import generated.cinemaService.relationControl.Customer_BookingStateSupervisor;
import generated.cinemaService.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinemaService.relationControl.BookingState_SeatSupervisor;
import generated.cinemaService.relationControl.BookingState_CustomerSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.BookingProxy;
import generated.cinemaService.proxies.IBooking;
import src.db.executer.PersistenceException;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Booking extends BookingState implements java.io.Serializable, IBooking
{
   //30 ===== GENERATED:      Attribute Section ======
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Booking(Integer id, MovieScreening screening, Seat seat, Customer customer, boolean objectOnly)
   throws ConstraintViolation, PersistenceException{
      super(id, screening, seat, customer, objectOnly);
      if(objectOnly) return;
   }
   public static Booking createFresh(MovieScreening screening, Seat seat, Customer customer)throws ConstraintViolation, PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("BookingState", "id, typeKey", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "Booking").toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Booking me = new Booking(id, screening, seat, customer, false);
      CinemaService.getInstance().addBookingProxy(new BookingProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getBookingCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Booking toBeDeleted = CinemaService.getInstance().getBooking(id);
      MovieScreening_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Customer_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      BookingState_MovieScreeningSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      BookingState_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      BookingState_CustomerSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getBookingCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("BookingState", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Booking instantiateRuntimeCopy(BookingProxy proxy, MovieScreening screening, Seat seat, Customer customer)throws ConstraintViolation, PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Booking(proxy.getId(), screening, seat, customer, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Booking getTheObject(){
      return this;
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
