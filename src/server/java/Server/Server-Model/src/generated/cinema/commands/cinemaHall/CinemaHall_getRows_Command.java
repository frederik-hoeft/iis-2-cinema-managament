/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.cinemaHall;
import generated.cinema.*;
import commands.*;
import java.util.Collection;
public class CinemaHall_getRows_Command extends ObjectCommand<CinemaHall, Collection<SeatRow>>{
   private static final long serialVersionUID = -940160573L;
   public CinemaHall_getRows_Command(CinemaHall receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getRows();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
