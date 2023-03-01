/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seat;
import generated.cinema.*;
import commands.*;
public class Seat_setBookingState_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = -617110602L;
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
