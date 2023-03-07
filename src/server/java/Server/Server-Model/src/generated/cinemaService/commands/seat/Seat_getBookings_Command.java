/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seat;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class Seat_getBookings_Command extends ObjectCommand<Seat, Collection<BookingState>>{
   private static final long serialVersionUID = 1543267741L;
   public Seat_getBookings_Command(Seat receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getBookings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
