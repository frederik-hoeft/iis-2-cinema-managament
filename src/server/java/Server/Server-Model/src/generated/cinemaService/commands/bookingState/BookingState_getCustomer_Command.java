/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.bookingState;
import generated.cinemaService.*;
import commands.*;
public class BookingState_getCustomer_Command extends ObjectCommand<BookingState, Customer>{
   private static final long serialVersionUID = -1555053510L;
   public BookingState_getCustomer_Command(BookingState receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getCustomer();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
