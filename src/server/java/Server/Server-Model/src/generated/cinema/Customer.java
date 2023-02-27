/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- Mode = No Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinema;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import exceptions.ConstraintViolation;
import java.util.List;
import generated.cinema.proxies.IBookingState;
import generated.cinema.relationControl.BookingState_CustomerSupervisor;
import generated.cinema.relationControl.Customer_BookingStateSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.CustomerProxy;
import observation.Observable;
import generated.cinema.proxies.ICustomer;
import generated.cinema.relationControl.*;
import generated.cinema.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Customer extends Observable implements java.io.Serializable, ICustomer
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer customerId;
   private String firstName;
   private String lastName;
   private String email;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Customer(Integer id, Integer customerId, String firstName, String lastName, String email, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.customerId = customerId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      if(objectOnly) return;
   }
   public static Customer createFresh(Integer customerId, String firstName, String lastName, String email)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = Cinema.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Customer", "id, typeKey, customerId, firstName, lastName, email", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "Customer").toString() + ", " + customerId.toString() + ", " + "'" + firstName + "'" + ", " + "'" + lastName + "'" + ", " + "'" + email + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Customer me = new Customer(id, customerId, firstName, lastName, email, false);
      Cinema.getInstance().addCustomerProxy(new CustomerProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!Cinema.getInstance().getCustomerCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Customer toBeDeleted = Cinema.getInstance().getCustomer(id);
      List<IBookingState> ownersInBookingState_Customer = BookingState_CustomerSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInBookingState_Customer.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association BookingState_Customer");
      BookingState_CustomerSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      Customer_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      Cinema.getInstance().getCustomerCache().remove(id);
      Cinema.getInstance().getDmlExecuter().delete("Customer", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Customer instantiateRuntimeCopy(CustomerProxy proxy, Integer customerId, String firstName, String lastName, String email){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Customer(proxy.getId(), customerId, firstName, lastName, email, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Customer getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ICustomer)) return false;
      return ((ICustomer)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Set<BookingState> getBookings() throws PersistenceException{
      Set<BookingState> result = new HashSet<>();
      for (IBookingState i : Customer_BookingStateSupervisor.getInstance().getBookings(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToBookings(BookingState arg) throws PersistenceException{
      Customer_BookingStateSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBookings(BookingState arg) throws PersistenceException{
      return Customer_BookingStateSupervisor.getInstance().remove(this, arg);
   }
   public Integer getCustomerId() {
      return this.customerId;
   }
   public void setCustomerId(Integer newCustomerId) throws PersistenceException{
      this.customerId = newCustomerId;
      try{Cinema.getInstance().getDmlExecuter().update("Customer", "customerId", newCustomerId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getFirstName() {
      return this.firstName;
   }
   public void setFirstName(String newFirstName) throws PersistenceException{
      this.firstName = newFirstName;
      try{Cinema.getInstance().getDmlExecuter().update("Customer", "firstName", "'" + newFirstName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getLastName() {
      return this.lastName;
   }
   public void setLastName(String newLastName) throws PersistenceException{
      this.lastName = newLastName;
      try{Cinema.getInstance().getDmlExecuter().update("Customer", "lastName", "'" + newLastName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getEmail() {
      return this.email;
   }
   public void setEmail(String newEmail) throws PersistenceException{
      this.email = newEmail;
      try{Cinema.getInstance().getDmlExecuter().update("Customer", "email", "'" + newEmail + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
