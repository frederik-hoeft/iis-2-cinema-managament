/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seat;
import generated.cinemaService.*;
import commands.*;
public class Seat_addToBookings_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = -1129797520L;
   private BookingState arg;
   public Seat_addToBookings_Command(Seat receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToBookings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
