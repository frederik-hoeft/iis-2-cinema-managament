/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seatRow;
import generated.cinema.*;
import commands.*;
public class SeatRow_delete_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = -902798541L;
   public SeatRow_delete_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{SeatRow.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}