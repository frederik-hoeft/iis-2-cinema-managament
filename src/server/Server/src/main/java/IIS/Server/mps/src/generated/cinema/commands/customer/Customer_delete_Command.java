/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.customer;
import generated.cinema.*;
import commands.*;
public class Customer_delete_Command extends ObjectCommand<Customer, Void>{
   private static final long serialVersionUID = 258588126L;
   public Customer_delete_Command(Customer receiver){
      super(receiver);
   }
   public void execute(){
      try{Customer.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
