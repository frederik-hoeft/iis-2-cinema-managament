/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seat;
import generated.cinema.*;
import commands.*;
public class Seat_getBookingState_Command extends ObjectCommand<Seat, java.util.Optional<BookingState>>{
   private static final long serialVersionUID = -420539735L;
   public Seat_getBookingState_Command(Seat receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getBookingState();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
