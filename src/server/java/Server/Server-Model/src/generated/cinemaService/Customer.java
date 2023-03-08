/**--- Generated at Wed Mar 08 17:23:05 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinemaService;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import exceptions.ConstraintViolation;
import java.util.List;
import generated.cinemaService.proxies.IBookingState;
import generated.cinemaService.relationControl.Customer_BookingStateSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.CustomerProxy;
import observation.Observable;
import generated.cinemaService.proxies.ICustomer;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Customer extends Observable implements java.io.Serializable, ICustomer
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private String firstName;
   private String lastName;
   private String email;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Customer(Integer id, String firstName, String lastName, String email, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      if(objectOnly) return;
   }
   public static Customer createFresh(String firstName, String lastName, String email)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Customer", "id, typeKey, firstName, lastName, email", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "Customer").toString() + ", " + "'" + firstName + "'" + ", " + "'" + lastName + "'" + ", " + "'" + email + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Customer me = new Customer(id, firstName, lastName, email, false);
      CinemaService.getInstance().addCustomerProxy(new CustomerProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getCustomerCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      Customer toBeDeleted = CinemaService.getInstance().getCustomer(id);
      List<IBookingState> targetsInCustomer_BookingState = Customer_BookingStateSupervisor.getInstance().getRelationData().getRelatedTargets(toBeDeleted);
      if(targetsInCustomer_BookingState.size()>0) throw new ConstraintViolation(" Deletion not possible: Object still contains other objects in Association Customer_BookingState");
      Customer_BookingStateSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getCustomerCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("Customer", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Customer instantiateRuntimeCopy(CustomerProxy proxy, String firstName, String lastName, String email){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Customer(proxy.getId(), firstName, lastName, email, true);
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
   public void addToBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      Customer_BookingStateSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBookings(BookingState arg) throws ConstraintViolation, PersistenceException{
      return Customer_BookingStateSupervisor.getInstance().remove(this, arg);
   }
   public String getFirstName() {
      return this.firstName;
   }
   public void setFirstName(String newFirstName) throws PersistenceException{
      this.firstName = newFirstName;
      try{CinemaService.getInstance().getDmlExecuter().update("Customer", "firstName", "'" + newFirstName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getLastName() {
      return this.lastName;
   }
   public void setLastName(String newLastName) throws PersistenceException{
      this.lastName = newLastName;
      try{CinemaService.getInstance().getDmlExecuter().update("Customer", "lastName", "'" + newLastName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public String getEmail() {
      return this.email;
   }
   public void setEmail(String newEmail) throws PersistenceException{
      this.email = newEmail;
      try{CinemaService.getInstance().getDmlExecuter().update("Customer", "email", "'" + newEmail + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
