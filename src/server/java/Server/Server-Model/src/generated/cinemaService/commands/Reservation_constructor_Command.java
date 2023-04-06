/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Reservation_constructor_Command extends ServiceCommand<Reservation>{
   private static final long serialVersionUID = -1233199910L;
   private MovieScreening screening;
   private Seat seat;
   private Customer customer;
   public Reservation_constructor_Command(MovieScreening screening, Seat seat, Customer customer){
      super();
      this.screening = screening;
      this.seat = seat;
      this.customer = customer;
   }
   public void execute(){
      try{this.result = Reservation.createFresh(screening, seat, customer);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public MovieScreening screening(){return screening;}
   public Seat seat(){return seat;}
   public Customer customer(){return customer;}
}
