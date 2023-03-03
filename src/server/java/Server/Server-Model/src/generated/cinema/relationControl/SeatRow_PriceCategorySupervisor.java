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
public class SeatRow_PriceCategorySupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static SeatRow_PriceCategorySupervisor theInstance = new SeatRow_PriceCategorySupervisor();
   private Relation<ISeatRow, IPriceCategory> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private SeatRow_PriceCategorySupervisor(){
      this.elements = new Relation<>("SeatRow_PriceCategory", "Cinema");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static SeatRow_PriceCategorySupervisor getInstance(){return theInstance;}
   public Relation<ISeatRow, IPriceCategory> getRelationData() {
      return this.elements;
   }
   public IPriceCategory getPrice(ISeatRow owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(ISeatRow owner, IPriceCategory target) throws PersistenceException{
      IPriceCategory targetOld = this.getPrice(owner); IPriceCategory targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   /** Used only by service class !! **/
   public void setAlreadyPersistent(ISeatRow owner, IPriceCategory target) {
      IPriceCategory targetOld = null; IPriceCategory targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(ISeatRow owner, IPriceCategory targetOld, IPriceCategory targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
