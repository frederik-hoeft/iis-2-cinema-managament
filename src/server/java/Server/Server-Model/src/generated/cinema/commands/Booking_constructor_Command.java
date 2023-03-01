/**--- Generated at Wed Mar 01 16:05:46 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Booking_constructor_Command extends ServiceCommand<Booking>{
   private static final long serialVersionUID = -1976034435L;
   private MovieScreening screening;
   private Seat seat;
   private Customer customer;
   private Integer  bookingId;
   public Booking_constructor_Command(MovieScreening screening, Seat seat, Customer customer, Integer  bookingId){
      super();
      this.screening = screening;
      this.seat = seat;
      this.customer = customer;
      this.bookingId = bookingId;
   }
   public void execute(){
      try{this.result = Booking.createFresh(screening, seat, customer, bookingId);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public MovieScreening screening(){return screening;}
   public Seat seat(){return seat;}
   public Customer customer(){return customer;}
   public Integer  bookingId(){return bookingId;}
}
