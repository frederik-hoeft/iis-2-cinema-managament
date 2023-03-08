/**--- Generated at Wed Mar 08 17:23:05 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.customer;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class Customer_getBookings_Command extends ObjectCommand<Customer, Collection<BookingState>>{
   private static final long serialVersionUID = -282319212L;
   public Customer_getBookings_Command(Customer receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getBookings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
