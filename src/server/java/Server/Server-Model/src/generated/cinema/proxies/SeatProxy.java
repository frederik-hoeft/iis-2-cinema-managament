/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import java.util.Optional;
import generated.cinema.Cinema;
import src.db.executer.*;
import generated.cinema.Seat;
import java.sql.ResultSet;
import java.util.Optional;
import generated.cinema.SeatRow;
import generated.cinema.relationControl.Seat_SeatRowSupervisor;
import generated.cinema.BookingState;
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
         rs = Cinema.getInstance().getDmlExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Seat", this.id);
         SeatRow row = Seat_SeatRowSupervisor.getInstance().getRow(this).getTheObject();
         Integer seatId = rs.get().getInt("seatId");
         return Seat.instantiateRuntimeCopy(this, row, seatId);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public SeatRow getRow() throws PersistenceException{
      return this.getTheObject().getRow();
   }
   public void setRow(SeatRow newRow)throws PersistenceException{
      this.getTheObject().setRow(newRow);
   }
   public Optional<BookingState> getBookingState() throws PersistenceException{
      return this.getTheObject().getBookingState();
   }
   public void setBookingState(BookingState newBookingState)throws PersistenceException{
      this.getTheObject().setBookingState(newBookingState);
   }
   public Integer getSeatId() {
      return this.getTheObject().getSeatId();
   }
   public void setSeatId(Integer newSeatId) throws PersistenceException{
      this.getTheObject().setSeatId(newSeatId);
   }
}
