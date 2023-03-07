/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class SeatRow_getSeats_Command extends ObjectCommand<SeatRow, Collection<Seat>>{
   private static final long serialVersionUID = 1221296594L;
   public SeatRow_getSeats_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getSeats();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
