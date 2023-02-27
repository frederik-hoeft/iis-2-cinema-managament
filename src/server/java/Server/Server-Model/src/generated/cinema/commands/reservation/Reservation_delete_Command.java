/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.reservation;
import generated.cinema.*;
import commands.*;
public class Reservation_delete_Command extends ObjectCommand<Reservation, Void>{
   private static final long serialVersionUID = -1464264374L;
   public Reservation_delete_Command(Reservation receiver){
      super(receiver);
   }
   public void execute(){
      try{Reservation.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
