/**--- Generated at Wed Mar 08 00:30:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.customer;
import generated.cinemaService.*;
import commands.*;
public class Customer_delete_Command extends ObjectCommand<Customer, Void>{
   private static final long serialVersionUID = 956974940L;
   public Customer_delete_Command(Customer receiver){
      super(receiver);
   }
   public void execute(){
      try{Customer.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
