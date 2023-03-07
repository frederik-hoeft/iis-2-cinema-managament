/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.customer;
import generated.cinemaService.*;
import commands.*;
public class Customer_addToBookings_Command extends ObjectCommand<Customer, Void>{
   private static final long serialVersionUID = 909182344L;
   private BookingState arg;
   public Customer_addToBookings_Command(Customer receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToBookings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
