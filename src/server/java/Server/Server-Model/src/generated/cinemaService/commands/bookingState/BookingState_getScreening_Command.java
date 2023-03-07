/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.bookingState;
import generated.cinemaService.*;
import commands.*;
public class BookingState_getScreening_Command extends ObjectCommand<BookingState, MovieScreening>{
   private static final long serialVersionUID = 418491517L;
   public BookingState_getScreening_Command(BookingState receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getScreening();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
