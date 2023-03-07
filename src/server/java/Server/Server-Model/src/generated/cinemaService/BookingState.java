/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
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
import observation.Observable;
import generated.cinemaService.proxies.IBookingState;
import generated.cinemaService.relationControl.*;
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
      if(objectOnly) return;
      try{MovieScreening_BookingStateSupervisor.getInstance().add(screening,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
      try{Seat_BookingStateSupervisor.getInstance().add(seat,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
      try{Customer_BookingStateSupervisor.getInstance().add(customer,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
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
      return MovieScreening_BookingStateSupervisor.getInstance().getScreening(this).getTheObject();
   }
   public Seat getSeat() throws PersistenceException{
      return Seat_BookingStateSupervisor.getInstance().getSeat(this).getTheObject();
   }
   public Customer getCustomer() throws PersistenceException{
      return Customer_BookingStateSupervisor.getInstance().getCustomer(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
