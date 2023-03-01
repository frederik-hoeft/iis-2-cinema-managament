/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seat;
import generated.cinema.*;
import commands.*;
public class Seat_setRow_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = -302714134L;
   private SeatRow arg;
   public Seat_setRow_Command(Seat receiver, SeatRow arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setRow(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public SeatRow arg(){return this.arg;}
}
