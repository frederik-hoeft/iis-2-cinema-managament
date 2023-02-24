/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import generated.cinema.BookingState;
import generated.cinema.MovieScreening;
import generated.cinema.Seat;
import generated.cinema.Customer;
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
   public void setScreening(MovieScreening newScreening)throws PersistenceException{
      this.getTheObject().setScreening(newScreening);
   }
   public Seat getSeat() throws PersistenceException{
      return this.getTheObject().getSeat();
   }
   public void setSeat(Seat newSeat)throws PersistenceException{
      this.getTheObject().setSeat(newSeat);
   }
   public Customer getCustomer() throws PersistenceException{
      return this.getTheObject().getCustomer();
   }
   public void setCustomer(Customer newCustomer)throws PersistenceException{
      this.getTheObject().setCustomer(newCustomer);
   }
}
