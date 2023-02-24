/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seat;
import generated.cinema.*;
import commands.*;
public class Seat_getRow_Command extends ObjectCommand<Seat, SeatRow>{
   private static final long serialVersionUID = -1702214355L;
   public Seat_getRow_Command(Seat receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getRow();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
