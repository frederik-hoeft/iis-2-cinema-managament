/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seatRow;
import generated.cinema.*;
import commands.*;
public class SeatRow_getHall_Command extends ObjectCommand<SeatRow, CinemaHall>{
   private static final long serialVersionUID = 205131958L;
   public SeatRow_getHall_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getHall();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
