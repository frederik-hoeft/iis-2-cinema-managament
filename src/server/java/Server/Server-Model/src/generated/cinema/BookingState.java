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
import observation.Observable;
import generated.cinema.proxies.IBookingState;
import generated.cinema.relationControl.*;
import generated.cinema.proxies.*;
import src.db.executer.PersistenceException;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public abstract class BookingState extends Observable implements java.io.Serializable, IBookingState
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   public BookingState(Integer id, MovieScreening screening, Seat seat, Customer customer, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      BookingState_MovieScreeningSupervisor.getInstance().set(this, screening);
      BookingState_SeatSupervisor.getInstance().set(this, seat);
      BookingState_CustomerSupervisor.getInstance().set(this, customer);
      if(objectOnly) return;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public BookingState getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IBookingState)) return false;
      return ((IBookingState)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public MovieScreening getScreening() throws PersistenceException{
      return BookingState_MovieScreeningSupervisor.getInstance().getScreening(this).getTheObject();
   }
   public void setScreening(MovieScreening newScreening)throws PersistenceException{
      BookingState_MovieScreeningSupervisor.getInstance().change(this, this.getScreening(), newScreening);
   }
   public Seat getSeat() throws PersistenceException{
      return BookingState_SeatSupervisor.getInstance().getSeat(this).getTheObject();
   }
   public void setSeat(Seat newSeat)throws PersistenceException{
      BookingState_SeatSupervisor.getInstance().change(this, this.getSeat(), newSeat);
   }
   public Customer getCustomer() throws PersistenceException{
      return BookingState_CustomerSupervisor.getInstance().getCustomer(this).getTheObject();
   }
   public void setCustomer(Customer newCustomer)throws PersistenceException{
      BookingState_CustomerSupervisor.getInstance().change(this, this.getCustomer(), newCustomer);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
