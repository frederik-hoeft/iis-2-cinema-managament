/**--- Generated at Wed Mar 01 16:05:46 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.customer;
import generated.cinema.*;
import commands.*;
import java.util.Collection;
public class Customer_getBookings_Command extends ObjectCommand<Customer, Collection<BookingState>>{
   private static final long serialVersionUID = 63176593L;
   public Customer_getBookings_Command(Customer receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getBookings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
