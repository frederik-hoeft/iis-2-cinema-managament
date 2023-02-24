/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seat;
import generated.cinema.*;
import commands.*;
public class Seat_delete_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = -1440275919L;
   public Seat_delete_Command(Seat receiver){
      super(receiver);
   }
   public void execute(){
      try{Seat.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
