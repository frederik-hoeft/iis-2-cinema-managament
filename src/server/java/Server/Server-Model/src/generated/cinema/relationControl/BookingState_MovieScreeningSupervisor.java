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
public class BookingState_MovieScreeningSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static BookingState_MovieScreeningSupervisor theInstance = new BookingState_MovieScreeningSupervisor();
   private Relation<IBookingState, IMovieScreening> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private BookingState_MovieScreeningSupervisor(){
      this.elements = new Relation<>("BookingState_MovieScreening", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static BookingState_MovieScreeningSupervisor getInstance(){return theInstance;}
   public Relation<IBookingState, IMovieScreening> getRelationData() {
      return this.elements;
   }
   public IMovieScreening getScreening(IBookingState owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IBookingState owner, IMovieScreening target) throws PersistenceException{
      IMovieScreening targetOld = this.getScreening(owner); IMovieScreening targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(IBookingState owner, IMovieScreening target) {
      IMovieScreening targetOld = null; IMovieScreening targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IBookingState owner, IMovieScreening targetOld, IMovieScreening targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
