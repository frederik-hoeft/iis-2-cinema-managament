/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seatRow;
import generated.cinema.*;
import commands.*;
public class SeatRow_setPrice_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = 1818757140L;
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
