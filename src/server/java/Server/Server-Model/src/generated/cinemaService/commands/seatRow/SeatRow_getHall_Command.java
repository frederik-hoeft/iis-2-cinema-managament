/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_getHall_Command extends ObjectCommand<SeatRow, CinemaHall>{
   private static final long serialVersionUID = -2110740163L;
   public SeatRow_getHall_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getHall();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
