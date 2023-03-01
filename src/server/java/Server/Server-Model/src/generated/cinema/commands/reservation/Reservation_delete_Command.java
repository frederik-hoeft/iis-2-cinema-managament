/**--- Generated at Wed Mar 01 16:05:46 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.reservation;
import generated.cinema.*;
import commands.*;
public class Reservation_delete_Command extends ObjectCommand<Reservation, Void>{
   private static final long serialVersionUID = 497051115L;
   public Reservation_delete_Command(Reservation receiver){
      super(receiver);
   }
   public void execute(){
      try{Reservation.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
