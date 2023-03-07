/**--- Generated at Tue Mar 07 22:26:24 CET 2023 
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
public class CinemaHall_MovieScreeningSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static CinemaHall_MovieScreeningSupervisor theInstance = new CinemaHall_MovieScreeningSupervisor();
   private Relation<ICinemaHall, IMovieScreening> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private CinemaHall_MovieScreeningSupervisor(){
      this.elements = new Relation<>("CinemaHall_MovieScreening", "CinemaService");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static CinemaHall_MovieScreeningSupervisor getInstance(){return theInstance;}
   public Relation<ICinemaHall, IMovieScreening> getRelationData() {
      return this.elements;
   }
   public Set<IMovieScreening> getScreenings(ICinemaHall owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(ICinemaHall owner, IMovieScreening target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(ICinemaHall owner, IMovieScreening target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(ICinemaHall owner, IMovieScreening target) throws ConstraintViolation, PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(ICinemaHall owner, IMovieScreening target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateSurjectivity(owner, target);
      return this.elements.removeElement(owner,target);
   }
   public ICinemaHall getHall(IMovieScreening target){
      return this.elements.getRelatedSources(target).get(0);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
