/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.bookingState;
import generated.cinemaService.*;
import commands.*;
public class BookingState_getSeat_Command extends ObjectCommand<BookingState, Seat>{
   private static final long serialVersionUID = -2016747010L;
   public BookingState_getSeat_Command(BookingState receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getSeat();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
