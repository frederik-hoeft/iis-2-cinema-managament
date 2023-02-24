/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
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
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class SeatRow_CinemaHallSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static SeatRow_CinemaHallSupervisor theInstance = new SeatRow_CinemaHallSupervisor();
   private Relation<ISeatRow, ICinemaHall> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private SeatRow_CinemaHallSupervisor(){
      this.elements = new Relation<>("SeatRow_CinemaHall", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static SeatRow_CinemaHallSupervisor getInstance(){return theInstance;}
   public Relation<ISeatRow, ICinemaHall> getRelationData() {
      return this.elements;
   }
   public ICinemaHall getHall(ISeatRow owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(ISeatRow owner, ICinemaHall target) throws PersistenceException{
      ICinemaHall targetOld = this.getHall(owner); ICinemaHall targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(ISeatRow owner, ICinemaHall target) {
      ICinemaHall targetOld = null; ICinemaHall targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(ISeatRow owner, ICinemaHall targetOld, ICinemaHall targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
