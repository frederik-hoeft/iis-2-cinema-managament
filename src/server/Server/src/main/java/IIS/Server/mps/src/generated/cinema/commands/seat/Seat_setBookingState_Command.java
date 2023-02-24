/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seat;
import generated.cinema.*;
import commands.*;
public class Seat_setBookingState_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = -442820839L;
   private BookingState arg;
   public Seat_setBookingState_Command(Seat receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setBookingState(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
