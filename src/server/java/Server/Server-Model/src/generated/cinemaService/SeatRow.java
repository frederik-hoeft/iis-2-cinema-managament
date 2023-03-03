/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinemaService;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import exceptions.ConstraintViolation;
import generated.cinemaService.relationControl.CinemaHall_SeatRowSupervisor;
import java.util.List;
import generated.cinemaService.proxies.ISeat;
import generated.cinemaService.relationControl.Seat_SeatRowSupervisor;
import generated.cinemaService.relationControl.SeatRow_CinemaHallSupervisor;
import generated.cinemaService.relationControl.SeatRow_PriceCategorySupervisor;
import generated.cinemaService.relationControl.SeatRow_SeatSupervisor;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.SeatRowProxy;
import observation.Observable;
import generated.cinemaService.proxies.ISeatRow;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class SeatRow extends Observable implements java.io.Serializable, ISeatRow
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private SeatRow(Integer id, CinemaHall hall, PriceCategory price, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      SeatRow_CinemaHallSupervisor.getInstance().set(this, hall);
      SeatRow_PriceCategorySupervisor.getInstance().set(this, price);
      if(objectOnly) return;
   }
   public static SeatRow createFresh(CinemaHall hall, PriceCategory price)throws PersistenceException{
      src.db.executer.PersistenceDMLExecuter dmlExecuter = CinemaService.getInstance().getDmlExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("SeatRow", "id, typeKey", 
         id.toString() + ", " + PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "SeatRow").toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      SeatRow me = new SeatRow(id, hall, price, false);
      CinemaService.getInstance().addSeatRowProxy(new SeatRowProxy(me));
      return me;
   }
   public static void delete(Integer id) throws ConstraintViolation, SQLException, NoConnectionException {
      if(!CinemaService.getInstance().getSeatRowCache().containsKey(id))throw new ConstraintViolation("Deletion not possible: " + "id " + id + " does not exist!");
      SeatRow toBeDeleted = CinemaService.getInstance().getSeatRow(id);
      CinemaHall_SeatRowSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      List<ISeat> ownersInSeat_SeatRow = Seat_SeatRowSupervisor.getInstance().getRelationData().getRelatedSources(toBeDeleted);
      if(ownersInSeat_SeatRow.size()>0) throw new ConstraintViolation(" Deletion not possible: Object is still referenced within TotalMap-Association Seat_SeatRow");
      Seat_SeatRowSupervisor.getInstance().getRelationData().removeAllPairsWithTarget(toBeDeleted);
      SeatRow_CinemaHallSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      SeatRow_PriceCategorySupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      SeatRow_SeatSupervisor.getInstance().getRelationData().removeAllPairsWithSource(toBeDeleted);
      CinemaService.getInstance().getSeatRowCache().remove(id);
      CinemaService.getInstance().getDmlExecuter().delete("SeatRow", id);
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static SeatRow instantiateRuntimeCopy(SeatRowProxy proxy, CinemaHall hall, PriceCategory price)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new SeatRow(proxy.getId(), hall, price, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public SeatRow getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISeatRow)) return false;
      return ((ISeatRow)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public CinemaHall getHall() throws PersistenceException{
      return SeatRow_CinemaHallSupervisor.getInstance().getHall(this).getTheObject();
   }
   public void setHall(CinemaHall newHall)throws PersistenceException{
      SeatRow_CinemaHallSupervisor.getInstance().change(this, this.getHall(), newHall);
   }
   public PriceCategory getPrice() throws PersistenceException{
      return SeatRow_PriceCategorySupervisor.getInstance().getPrice(this).getTheObject();
   }
   public void setPrice(PriceCategory newPrice)throws PersistenceException{
      SeatRow_PriceCategorySupervisor.getInstance().change(this, this.getPrice(), newPrice);
   }
   public Set<Seat> getSeats() throws PersistenceException{
      Set<Seat> result = new HashSet<>();
      for (ISeat i : SeatRow_SeatSupervisor.getInstance().getSeats(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToSeats(Seat arg) throws PersistenceException{
      SeatRow_SeatSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromSeats(Seat arg) throws PersistenceException{
      return SeatRow_SeatSupervisor.getInstance().remove(this, arg);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
