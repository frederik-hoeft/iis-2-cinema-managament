/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.Reservation;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.MovieScreening;
import generated.cinema.relationControl.BookingState_MovieScreeningSupervisor;
import generated.cinema.Seat;
import generated.cinema.relationControl.BookingState_SeatSupervisor;
import generated.cinema.Customer;
import generated.cinema.relationControl.BookingState_CustomerSupervisor;
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
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("BookingState", this.id);
         MovieScreening screening = BookingState_MovieScreeningSupervisor.getInstance().getScreening(this).getTheObject();
         Seat seat = BookingState_SeatSupervisor.getInstance().getSeat(this).getTheObject();
         Customer customer = BookingState_CustomerSupervisor.getInstance().getCustomer(this).getTheObject();
         Integer reservationId = rs.get().getInt("reservationId");
         return Reservation.instantiateRuntimeCopy(this, screening, seat, customer, reservationId);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Integer getReservationId() {
      return this.getTheObject().getReservationId();
   }
   public void setReservationId(Integer newReservationId) throws PersistenceException{
      this.getTheObject().setReservationId(newReservationId);
   }
}
