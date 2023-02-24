/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Customer_constructor_Command extends ServiceCommand<Customer>{
   private static final long serialVersionUID = 363976439L;
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
