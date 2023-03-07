/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import generated.cinemaService.BookingState;
import generated.cinemaService.MovieScreening;
import generated.cinemaService.Seat;
import generated.cinemaService.Customer;
public abstract class BookingStateProxy implements IBookingState{
   public abstract BookingState getTheObject();
   public boolean equals(Object o) {
      if(!(o instanceof IBookingState)) return false;
      return ((IBookingState)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public MovieScreening getScreening() throws PersistenceException{
      return this.getTheObject().getScreening();
   }
   public Seat getSeat() throws PersistenceException{
      return this.getTheObject().getSeat();
   }
   public Customer getCustomer() throws PersistenceException{
      return this.getTheObject().getCustomer();
   }
}
