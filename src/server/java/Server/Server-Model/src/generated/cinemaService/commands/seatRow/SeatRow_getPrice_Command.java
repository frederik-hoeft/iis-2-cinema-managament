/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_getPrice_Command extends ObjectCommand<SeatRow, PriceCategory>{
   private static final long serialVersionUID = 2028144613L;
   public SeatRow_getPrice_Command(SeatRow receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getPrice();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
