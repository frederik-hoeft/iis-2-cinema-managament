/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
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
public class MovieScreeninig_MovieSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static MovieScreeninig_MovieSupervisor theInstance = new MovieScreeninig_MovieSupervisor();
   private Relation<IMovieScreening, IMovie> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private MovieScreeninig_MovieSupervisor(){
      this.elements = new Relation<>("MovieScreeninig_Movie", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static MovieScreeninig_MovieSupervisor getInstance(){return theInstance;}
   public Relation<IMovieScreening, IMovie> getRelationData() {
      return this.elements;
   }
   public IMovie getMovie(IMovieScreening owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IMovieScreening owner, IMovie target) throws PersistenceException{
      IMovie targetOld = this.getMovie(owner); IMovie targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(IMovieScreening owner, IMovie target) {
      IMovie targetOld = null; IMovie targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IMovieScreening owner, IMovie targetOld, IMovie targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
