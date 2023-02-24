/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.Booking;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.MovieScreening;
import generated.cinema.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinema.Seat;
import generated.cinema.relationControl.BookingState_SeatSupervisor;
import generated.cinema.Customer;
import generated.cinema.relationControl.BookingState_CustomerSupervisor;
public class BookingProxy extends BookingStateProxy implements IBooking{
   private Integer id;
   private Optional<Booking> theObject;
   public BookingProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public BookingProxy(Booking theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Booking getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Booking";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IBooking)) return false;
      return ((IBooking)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Booking load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("BookingState", this.id);
         MovieScreening screening = BookingState_MovieScreeningSupervisor.getInstance().getScreening(this).getTheObject();
         Seat seat = BookingState_SeatSupervisor.getInstance().getSeat(this).getTheObject();
         Customer customer = BookingState_CustomerSupervisor.getInstance().getCustomer(this).getTheObject();
         Integer bookingId = rs.get().getInt("bookingId");
         return Booking.instantiateRuntimeCopy(this, screening, seat, customer, bookingId);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Integer getBookingId() {
      return this.getTheObject().getBookingId();
   }
   public void setBookingId(Integer newBookingId) throws PersistenceException{
      this.getTheObject().setBookingId(newBookingId);
   }
}
