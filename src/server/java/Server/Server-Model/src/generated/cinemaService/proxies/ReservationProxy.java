/**--- Generated at Tue Mar 07 22:26:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.Reservation;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.relationControl.MovieScreening_BookingStateSupervisor;
import generated.cinemaService.Seat;
import generated.cinemaService.relationControl.Seat_BookingStateSupervisor;
import generated.cinemaService.Customer;
import generated.cinemaService.relationControl.Customer_BookingStateSupervisor;
public class ReservationProxy extends BookingStateProxy implements IReservation{
   private Integer id;
   private Optional<Reservation> theObject;
   public ReservationProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public ReservationProxy(Reservation theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Reservation getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Reservation";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IReservation)) return false;
      return ((IReservation)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Reservation load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("BookingState", this.id);
         MovieScreening screening = MovieScreening_BookingStateSupervisor.getInstance().getScreening(this).getTheObject();
         Seat seat = Seat_BookingStateSupervisor.getInstance().getSeat(this).getTheObject();
         Customer customer = Customer_BookingStateSupervisor.getInstance().getCustomer(this).getTheObject();
         return Reservation.instantiateRuntimeCopy(this, screening, seat, customer);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
}
