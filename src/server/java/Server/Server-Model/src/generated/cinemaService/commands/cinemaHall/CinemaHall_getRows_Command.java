/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class CinemaHall_getRows_Command extends ObjectCommand<CinemaHall, Collection<SeatRow>>{
   private static final long serialVersionUID = 37132408L;
   public CinemaHall_getRows_Command(CinemaHall receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getRows();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
