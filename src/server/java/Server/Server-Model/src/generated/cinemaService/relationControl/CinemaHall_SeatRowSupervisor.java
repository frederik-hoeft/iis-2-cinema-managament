/**--- Generated at Wed Mar 08 17:23:06 CET 2023 
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
import java.util.List;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class CinemaHall_SeatRowSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static CinemaHall_SeatRowSupervisor theInstance = new CinemaHall_SeatRowSupervisor();
   private Relation<ICinemaHall, ISeatRow> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private CinemaHall_SeatRowSupervisor(){
      this.elements = new Relation<>("CinemaHall_SeatRow", "CinemaService");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static CinemaHall_SeatRowSupervisor getInstance(){return theInstance;}
   public Relation<ICinemaHall, ISeatRow> getRelationData() {
      return this.elements;
   }
   public List<ISeatRow> getRows(ICinemaHall owner){
      return this.elements.getRelatedTargets(owner);
   }
   public void add(ICinemaHall owner, ISeatRow target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(ICinemaHall owner, ISeatRow target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(ICinemaHall owner, ISeatRow target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateSurjectivity(owner, target);
      return this.elements.removeElement(owner,target);
   }
   public ICinemaHall getHall(ISeatRow target){
      return this.elements.getRelatedSources(target).get(0);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
