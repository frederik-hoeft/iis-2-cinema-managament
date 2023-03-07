/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.Booking;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinemaService.Seat;
import generated.cinemaService.relationControl.BookingState_SeatSupervisor;
import generated.cinemaService.Customer;
import generated.cinemaService.relationControl.BookingState_CustomerSupervisor;
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
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("BookingState", this.id);
         MovieScreening screening = BookingState_MovieScreeningSupervisor.getInstance().getScreening(this).getTheObject();
         Seat seat = BookingState_SeatSupervisor.getInstance().getSeat(this).getTheObject();
         Customer customer = BookingState_CustomerSupervisor.getInstance().getCustomer(this).getTheObject();
         return Booking.instantiateRuntimeCopy(this, screening, seat, customer);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
}
