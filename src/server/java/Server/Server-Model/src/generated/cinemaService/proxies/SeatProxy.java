/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinemaService.CinemaService;
import src.db.executer.*;
import generated.cinemaService.Seat;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinemaService.SeatRow;
import generated.cinemaService.relationControl.Seat_SeatRowSupervisor;
public class SeatProxy implements ISeat{
   private Integer id;
   private Optional<Seat> theObject;
   public SeatProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public SeatProxy(Seat theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Seat getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Seat";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISeat)) return false;
      return ((ISeat)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Seat load() throws PersistenceException {
      Optional<ResultSet> rs = Optional.empty();
      try {
         rs = CinemaService.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Seat", this.id);
         SeatRow row = Seat_SeatRowSupervisor.getInstance().getRow(this).getTheObject();
         String name = rs.get().getString("name");
         return Seat.instantiateRuntimeCopy(this, row, name);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public SeatRow getRow() throws PersistenceException{
      return this.getTheObject().getRow();
   }
   public void setRow(SeatRow newRow)throws PersistenceException{
      this.getTheObject().setRow(newRow);
   }
   public String getName() {
      return this.getTheObject().getName();
   }
   public void setName(String newName) throws PersistenceException{
      this.getTheObject().setName(newName);
   }
}
