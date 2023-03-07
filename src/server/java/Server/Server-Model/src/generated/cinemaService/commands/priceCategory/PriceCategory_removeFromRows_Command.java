/**--- Generated at Tue Mar 07 13:26:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.priceCategory;
import generated.cinemaService.*;
import commands.*;
public class PriceCategory_removeFromRows_Command extends ObjectCommand<PriceCategory, Boolean>{
   private static final long serialVersionUID = 1384543902L;
   private SeatRow arg;
   public PriceCategory_removeFromRows_Command(PriceCategory receiver, SeatRow arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromRows(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public SeatRow arg(){return this.arg;}
}
