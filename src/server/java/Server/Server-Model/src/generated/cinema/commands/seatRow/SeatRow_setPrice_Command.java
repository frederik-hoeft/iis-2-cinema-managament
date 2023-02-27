/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seatRow;
import generated.cinema.*;
import commands.*;
public class SeatRow_setPrice_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = -1215217626L;
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
