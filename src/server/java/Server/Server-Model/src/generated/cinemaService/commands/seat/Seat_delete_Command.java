/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.seat;
import generated.cinemaService.*;
import commands.*;
public class Seat_delete_Command extends ObjectCommand<Seat, Void>{
   private static final long serialVersionUID = -586054280L;
   public Seat_delete_Command(Seat receiver){
      super(receiver);
   }
   public void execute(){
      try{Seat.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
