/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_delete_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = -612692243L;
   public SeatRow_delete_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{SeatRow.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
