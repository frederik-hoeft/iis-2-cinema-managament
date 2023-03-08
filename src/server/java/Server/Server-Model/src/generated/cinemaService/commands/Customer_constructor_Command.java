/**--- Generated at Wed Mar 08 17:23:05 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Customer_constructor_Command extends ServiceCommand<Customer>{
   private static final long serialVersionUID = -1138628681L;
   private String  firstName;
   private String  lastName;
   private String  email;
   public Customer_constructor_Command(String  firstName, String  lastName, String  email){
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }
   public void execute(){
      try{this.result = Customer.createFresh(firstName, lastName, email);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public String  firstName(){return firstName;}
   public String  lastName(){return lastName;}
   public String  email(){return email;}
}
