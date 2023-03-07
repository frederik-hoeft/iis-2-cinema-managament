/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_addToSeats_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = 1217658653L;
   private Seat arg;
   public SeatRow_addToSeats_Command(SeatRow receiver, Seat arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToSeats(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public Seat arg(){return this.arg;}
}
