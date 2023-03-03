/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seatRow;
import generated.cinema.*;
import commands.*;
import java.util.Collection;
public class SeatRow_getSeats_Command extends ObjectCommand<SeatRow, Collection<Seat>>{
   private static final long serialVersionUID = -831852953L;
   public SeatRow_getSeats_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getSeats();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
