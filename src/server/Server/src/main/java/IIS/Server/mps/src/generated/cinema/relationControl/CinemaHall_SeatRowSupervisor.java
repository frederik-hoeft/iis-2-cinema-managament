/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
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
import java.util.Set;
import java.util.stream.Collectors;
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
      this.elements = new Relation<>("CinemaHall_SeatRow", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static CinemaHall_SeatRowSupervisor getInstance(){return theInstance;}
   public Relation<ICinemaHall, ISeatRow> getRelationData() {
      return this.elements;
   }
   public Set<ISeatRow> getRows(ICinemaHall owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(ICinemaHall owner, ISeatRow target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(ICinemaHall owner, ISeatRow target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(ICinemaHall owner, ISeatRow target) throws PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(ICinemaHall owner, ISeatRow target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
