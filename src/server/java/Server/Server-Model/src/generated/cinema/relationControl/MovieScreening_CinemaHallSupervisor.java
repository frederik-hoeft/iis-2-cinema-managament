/**--- Generated at Wed Mar 01 16:05:48 CET 2023 
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
public class MovieScreening_CinemaHallSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static MovieScreening_CinemaHallSupervisor theInstance = new MovieScreening_CinemaHallSupervisor();
   private Relation<IMovieScreening, ICinemaHall> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private MovieScreening_CinemaHallSupervisor(){
      this.elements = new Relation<>("MovieScreening_CinemaHall", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static MovieScreening_CinemaHallSupervisor getInstance(){return theInstance;}
   public Relation<IMovieScreening, ICinemaHall> getRelationData() {
      return this.elements;
   }
   public ICinemaHall getHall(IMovieScreening owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IMovieScreening owner, ICinemaHall target) throws PersistenceException{
      ICinemaHall targetOld = this.getHall(owner); ICinemaHall targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(IMovieScreening owner, ICinemaHall target) {
      ICinemaHall targetOld = null; ICinemaHall targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IMovieScreening owner, ICinemaHall targetOld, ICinemaHall targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
