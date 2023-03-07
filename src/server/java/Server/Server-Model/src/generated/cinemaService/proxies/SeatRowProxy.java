/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.SeatRow;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.PriceCategory;
import generated.cinemaService.relationControl.SeatRow_PriceCategorySupervisor;
import generated.cinemaService.Seat;
import java.util.Set;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.relationControl.CinemaHall_SeatRowSupervisor;
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
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("SeatRow", this.id);
         PriceCategory price = SeatRow_PriceCategorySupervisor.getInstance().getPrice(this).getTheObject();
         String name = rs.get().getString("name");
         CinemaHall hall = CinemaHall_SeatRowSupervisor.getInstance().getHall(this).getTheObject();
         return SeatRow.instantiateRuntimeCopy(this, price, name, hall);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
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
   public String getName() {
      return this.getTheObject().getName();
   }
   public void setName(String newName) throws PersistenceException{
      this.getTheObject().setName(newName);
   }
   public CinemaHall getHall() throws PersistenceException{
      return this.getTheObject().getHall();
   }
}
