/**--- Generated at Tue Mar 07 13:35:38 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Booking_constructor_Command extends ServiceCommand<Booking>{
   private static final long serialVersionUID = -858020154L;
   private MovieScreening screening;
   private Seat seat;
   private Customer customer;
   public Booking_constructor_Command(MovieScreening screening, Seat seat, Customer customer){
      super();
      this.screening = screening;
      this.seat = seat;
      this.customer = customer;
   }
   public void execute(){
      try{this.result = Booking.createFresh(screening, seat, customer);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public MovieScreening screening(){return screening;}
   public Seat seat(){return seat;}
   public Customer customer(){return customer;}
}
