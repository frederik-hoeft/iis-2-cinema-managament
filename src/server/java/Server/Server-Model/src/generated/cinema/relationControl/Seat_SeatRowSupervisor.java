/**--- Generated at Fri Mar 03 01:14:20 CET 2023 
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
public class Seat_SeatRowSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Seat_SeatRowSupervisor theInstance = new Seat_SeatRowSupervisor();
   private Relation<ISeat, ISeatRow> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Seat_SeatRowSupervisor(){
      this.elements = new Relation<>("Seat_SeatRow", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static Seat_SeatRowSupervisor getInstance(){return theInstance;}
   public Relation<ISeat, ISeatRow> getRelationData() {
      return this.elements;
   }
   public ISeatRow getRow(ISeat owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(ISeat owner, ISeatRow target) throws PersistenceException{
      ISeatRow targetOld = this.getRow(owner); ISeatRow targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(ISeat owner, ISeatRow target) {
      ISeatRow targetOld = null; ISeatRow targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(ISeat owner, ISeatRow targetOld, ISeatRow targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
