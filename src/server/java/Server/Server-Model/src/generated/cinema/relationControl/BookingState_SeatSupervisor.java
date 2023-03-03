/**--- Generated at Fri Mar 03 01:14:20 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinema.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import src.db.executer.PersistenceException;
import generated.cinema.proxies.*;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class BookingState_SeatSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static BookingState_SeatSupervisor theInstance = new BookingState_SeatSupervisor();
   private Relation<IBookingState, ISeat> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private BookingState_SeatSupervisor(){
      this.elements = new Relation<>("BookingState_Seat", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static BookingState_SeatSupervisor getInstance(){return theInstance;}
   public Relation<IBookingState, ISeat> getRelationData() {
      return this.elements;
   }
   public ISeat getSeat(IBookingState owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IBookingState owner, ISeat target) throws PersistenceException{
      ISeat targetOld = this.getSeat(owner); ISeat targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(IBookingState owner, ISeat target) {
      ISeat targetOld = null; ISeat targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IBookingState owner, ISeat targetOld, ISeat targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
