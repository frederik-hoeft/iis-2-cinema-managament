/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.booking;
import generated.cinema.*;
import commands.*;
public class Booking_delete_Command extends ObjectCommand<Booking, Void>{
   private static final long serialVersionUID = 564051393L;
   public Booking_delete_Command(Booking receiver){
      super(receiver);
   }
   public void execute(){
      try{Booking.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
