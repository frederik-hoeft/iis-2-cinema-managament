/**--- Generated at Tue Mar 07 13:26:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.priceCategory;
import generated.cinemaService.*;
import commands.*;
public class PriceCategory_addToRows_Command extends ObjectCommand<PriceCategory, Void>{
   private static final long serialVersionUID = -1026598646L;
   private SeatRow arg;
   public PriceCategory_addToRows_Command(PriceCategory receiver, SeatRow arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToRows(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public SeatRow arg(){return this.arg;}
}
