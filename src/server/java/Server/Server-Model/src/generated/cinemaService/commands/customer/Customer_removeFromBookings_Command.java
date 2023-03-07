/**--- Generated at Tue Mar 07 22:26:23 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.customer;
import generated.cinemaService.*;
import commands.*;
public class Customer_removeFromBookings_Command extends ObjectCommand<Customer, Boolean>{
   private static final long serialVersionUID = -26549448L;
   private BookingState arg;
   public Customer_removeFromBookings_Command(Customer receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromBookings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
