/**--- Generated at Wed Mar 08 00:30:21 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinemaService.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import src.db.executer.PersistenceException;
import generated.cinemaService.proxies.*;
import exceptions.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;
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
      this.elements = new Relation<>("Seat_BookingState", "CinemaService");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static Seat_BookingStateSupervisor getInstance(){return theInstance;}
   public Relation<ISeat, IBookingState> getRelationData() {
      return this.elements;
   }
   public Set<IBookingState> getBookings(ISeat owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(ISeat owner, IBookingState target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(ISeat owner, IBookingState target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(ISeat owner, IBookingState target) throws ConstraintViolation, PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(ISeat owner, IBookingState target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateSurjectivity(owner, target);
      return this.elements.removeElement(owner,target);
   }
   public ISeat getSeat(IBookingState target){
      return this.elements.getRelatedSources(target).get(0);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
