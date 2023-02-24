/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- Mode = No Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinema.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import src.db.executer.PersistenceException;
import generated.cinema.proxies.*;
import java.util.Optional;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Seat_BookingStateSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Seat_BookingStateSupervisor theInstance = new Seat_BookingStateSupervisor();
   private Relation<ISeat, IBookingState> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Seat_BookingStateSupervisor(){
      this.elements = new Relation<>("Seat_BookingState", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static Seat_BookingStateSupervisor getInstance(){return theInstance;}
   public Relation<ISeat, IBookingState> getRelationData() {
      return this.elements;
   }
   public Optional<IBookingState> getBookingState(ISeat owner){
      return (this.elements.getRelatedTargets(owner).size() == 0) ? Optional.empty() : Optional.of(this.elements.getRelatedTargets(owner).get(0));
   }
   public void set(ISeat owner, IBookingState target) throws PersistenceException{
      IBookingState targetOld = this.getBookingState(owner).isPresent() ? this.getBookingState(owner).get() : null; IBookingState targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(ISeat owner, IBookingState target) {
      IBookingState targetOld = null; IBookingState targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(ISeat owner, IBookingState targetOld, IBookingState targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
