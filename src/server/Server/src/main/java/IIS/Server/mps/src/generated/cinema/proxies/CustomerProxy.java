/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.Customer;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.BookingState;
import java.util.Set;
public class CustomerProxy implements ICustomer{
   private Integer id;
   private Optional<Customer> theObject;
   public CustomerProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public CustomerProxy(Customer theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Customer getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Customer";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ICustomer)) return false;
      return ((ICustomer)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Customer load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Customer", this.id);
         Integer customerId = rs.get().getInt("customerId");
         String firstName = rs.get().getString("firstName");
         String lastName = rs.get().getString("lastName");
         String email = rs.get().getString("email");
         return Customer.instantiateRuntimeCopy(this, customerId, firstName, lastName, email);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<BookingState> getBookings() throws PersistenceException{
      return this.getTheObject().getBookings();
   }
   public void addToBookings(BookingState arg) throws PersistenceException{
      this.getTheObject().addToBookings(arg);
   }
   public boolean removeFromBookings(BookingState arg) throws PersistenceException{
      return this.getTheObject().removeFromBookings(arg);
   }
   public Integer getCustomerId() {
      return this.getTheObject().getCustomerId();
   }
   public void setCustomerId(Integer newCustomerId) throws PersistenceException{
      this.getTheObject().setCustomerId(newCustomerId);
   }
   public String getFirstName() {
      return this.getTheObject().getFirstName();
   }
   public void setFirstName(String newFirstName) throws PersistenceException{
      this.getTheObject().setFirstName(newFirstName);
   }
   public String getLastName() {
      return this.getTheObject().getLastName();
   }
   public void setLastName(String newLastName) throws PersistenceException{
      this.getTheObject().setLastName(newLastName);
   }
   public String getEmail() {
      return this.getTheObject().getEmail();
   }
   public void setEmail(String newEmail) throws PersistenceException{
      this.getTheObject().setEmail(newEmail);
   }
}
