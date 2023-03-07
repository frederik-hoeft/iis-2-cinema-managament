/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.booking;
import generated.cinemaService.*;
import commands.*;
public class Booking_delete_Command extends ObjectCommand<Booking, Void>{
   private static final long serialVersionUID = 1541451722L;
   public Booking_delete_Command(Booking receiver){
      super(receiver);
   }
   public void execute(){
      try{Booking.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
