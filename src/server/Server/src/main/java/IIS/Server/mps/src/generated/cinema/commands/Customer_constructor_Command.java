/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Customer_constructor_Command extends ServiceCommand<Customer>{
   private static final long serialVersionUID = 60083475L;
   private Integer  customerId;
   private String  firstName;
   private String  lastName;
   private String  email;
   public Customer_constructor_Command(Integer  customerId, String  firstName, String  lastName, String  email){
      super();
      this.customerId = customerId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }
   public void execute(){
      try{this.result = Customer.createFresh(customerId, firstName, lastName, email);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public Integer  customerId(){return customerId;}
   public String  firstName(){return firstName;}
   public String  lastName(){return lastName;}
   public String  email(){return email;}
}
