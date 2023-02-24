/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.SeatRow;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.CinemaHall;
import generated.cinema.relationControl.SeatRow_CinemaHallSupervisor;
import generated.cinema.PriceCategory;
import generated.cinema.relationControl.SeatRow_PriceCategorySupervisor;
import generated.cinema.Seat;
import java.util.Set;
public class SeatRowProxy implements ISeatRow{
   private Integer id;
   private Optional<SeatRow> theObject;
   public SeatRowProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public SeatRowProxy(SeatRow theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public SeatRow getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "SeatRow";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISeatRow)) return false;
      return ((ISeatRow)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private SeatRow load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("SeatRow", this.id);
         CinemaHall hall = SeatRow_CinemaHallSupervisor.getInstance().getHall(this).getTheObject();
         PriceCategory price = SeatRow_PriceCategorySupervisor.getInstance().getPrice(this).getTheObject();
         Integer rowId = rs.get().getInt("rowId");
         return SeatRow.instantiateRuntimeCopy(this, hall, price, rowId);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public CinemaHall getHall() throws PersistenceException{
      return this.getTheObject().getHall();
   }
   public void setHall(CinemaHall newHall)throws PersistenceException{
      this.getTheObject().setHall(newHall);
   }
   public PriceCategory getPrice() throws PersistenceException{
      return this.getTheObject().getPrice();
   }
   public void setPrice(PriceCategory newPrice)throws PersistenceException{
      this.getTheObject().setPrice(newPrice);
   }
   public Set<Seat> getSeats() throws PersistenceException{
      return this.getTheObject().getSeats();
   }
   public void addToSeats(Seat arg) throws PersistenceException{
      this.getTheObject().addToSeats(arg);
   }
   public boolean removeFromSeats(Seat arg) throws PersistenceException{
      return this.getTheObject().removeFromSeats(arg);
   }
   public Integer getRowId() {
      return this.getTheObject().getRowId();
   }
   public void setRowId(Integer newRowId) throws PersistenceException{
      this.getTheObject().setRowId(newRowId);
   }
}
