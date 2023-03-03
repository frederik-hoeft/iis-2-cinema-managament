/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.bookingState;
import generated.cinemaService.*;
import commands.*;
public class BookingState_setCustomer_Command extends ObjectCommand<BookingState, Void>{
   private static final long serialVersionUID = 1330768159L;
   private Customer arg;
   public BookingState_setCustomer_Command(BookingState receiver, Customer arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setCustomer(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public Customer arg(){return this.arg;}
}
