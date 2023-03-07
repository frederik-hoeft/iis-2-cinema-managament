/**--- Generated at Tue Mar 07 13:26:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.priceCategory;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class PriceCategory_getRows_Command extends ObjectCommand<PriceCategory, Collection<SeatRow>>{
   private static final long serialVersionUID = -437026483L;
   public PriceCategory_getRows_Command(PriceCategory receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getRows();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
