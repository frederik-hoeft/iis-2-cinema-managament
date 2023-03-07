/**--- Generated at Tue Mar 07 13:35:41 CET 2023 
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
import java.util.Set;
import java.util.stream.Collectors;
import exceptions.ConstraintViolation;
import java.util.List;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class SeatRow_SeatSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static SeatRow_SeatSupervisor theInstance = new SeatRow_SeatSupervisor();
   private Relation<ISeatRow, ISeat> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private SeatRow_SeatSupervisor(){
      this.elements = new Relation<>("SeatRow_Seat", "CinemaService");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static SeatRow_SeatSupervisor getInstance(){return theInstance;}
   public Relation<ISeatRow, ISeat> getRelationData() {
      return this.elements;
   }
   public List<ISeat> getSeats(ISeatRow owner){
      return this.elements.getRelatedTargets(owner);
   }
   public void add(ISeatRow owner, ISeat target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(ISeatRow owner, ISeat target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(ISeatRow owner, ISeat target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateSurjectivity(owner, target);
      return this.elements.removeElement(owner,target);
   }
   public ISeatRow getRow(ISeat target){
      return this.elements.getRelatedSources(target).get(0);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
