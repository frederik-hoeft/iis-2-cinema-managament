/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
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
public class MovieScreening_BookingStateSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static MovieScreening_BookingStateSupervisor theInstance = new MovieScreening_BookingStateSupervisor();
   private Relation<IMovieScreening, IBookingState> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private MovieScreening_BookingStateSupervisor(){
      this.elements = new Relation<>("MovieScreening_BookingState", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static MovieScreening_BookingStateSupervisor getInstance(){return theInstance;}
   public Relation<IMovieScreening, IBookingState> getRelationData() {
      return this.elements;
   }
   public Set<IBookingState> getBookingStates(IMovieScreening owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(IMovieScreening owner, IBookingState target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(IMovieScreening owner, IBookingState target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(IMovieScreening owner, IBookingState target) throws PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(IMovieScreening owner, IBookingState target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
