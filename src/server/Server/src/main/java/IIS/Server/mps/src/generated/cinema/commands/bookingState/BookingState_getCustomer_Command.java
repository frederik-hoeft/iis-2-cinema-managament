/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.bookingState;
import generated.cinema.*;
import commands.*;
public class BookingState_getCustomer_Command extends ObjectCommand<BookingState, Customer>{
   private static final long serialVersionUID = -1494402249L;
   public BookingState_getCustomer_Command(BookingState receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getCustomer();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}