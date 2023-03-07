/**--- Generated at Tue Mar 07 13:35:38 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_removeFromSeats_Command extends ObjectCommand<SeatRow, Boolean>{
   private static final long serialVersionUID = -1799000589L;
   private Seat arg;
   public SeatRow_removeFromSeats_Command(SeatRow receiver, Seat arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromSeats(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public Seat arg(){return this.arg;}
}
