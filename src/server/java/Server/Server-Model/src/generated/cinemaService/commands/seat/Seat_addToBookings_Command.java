/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seat;
import generated.cinemaService.*;
import commands.*;
public class Seat_addToBookings_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = 660980099L;
   private BookingState arg;
   public Seat_addToBookings_Command(Seat receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToBookings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
