/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.customer;
import generated.cinema.*;
import commands.*;
public class Customer_removeFromBookings_Command extends ObjectCommand<Customer, Boolean>{
   private static final long serialVersionUID = 883782471L;
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
