/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.bookingState;
import generated.cinema.*;
import commands.*;
public class BookingState_getSeat_Command extends ObjectCommand<BookingState, Seat>{
   private static final long serialVersionUID = -732757570L;
   public BookingState_getSeat_Command(BookingState receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getSeat();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
