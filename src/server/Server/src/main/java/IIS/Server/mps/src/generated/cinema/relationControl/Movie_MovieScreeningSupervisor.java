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
public class Movie_MovieScreeningSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Movie_MovieScreeningSupervisor theInstance = new Movie_MovieScreeningSupervisor();
   private Relation<IMovie, IMovieScreening> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Movie_MovieScreeningSupervisor(){
      this.elements = new Relation<>("Movie_MovieScreening", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static Movie_MovieScreeningSupervisor getInstance(){return theInstance;}
   public Relation<IMovie, IMovieScreening> getRelationData() {
      return this.elements;
   }
   public Set<IMovieScreening> getScreenings(IMovie owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(IMovie owner, IMovieScreening target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   /** Used only by service class !! **/
   public void addAlreadyPersistent(IMovie owner, IMovieScreening target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(IMovie owner, IMovieScreening target) throws PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(IMovie owner, IMovieScreening target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
