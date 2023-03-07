/**--- Generated at Tue Mar 07 13:35:38 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seatRow;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_setPrice_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = -904405498L;
   private PriceCategory arg;
   public SeatRow_setPrice_Command(SeatRow receiver, PriceCategory arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setPrice(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public PriceCategory arg(){return this.arg;}
}
