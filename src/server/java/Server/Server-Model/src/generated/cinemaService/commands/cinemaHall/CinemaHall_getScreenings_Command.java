/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class CinemaHall_getScreenings_Command extends ObjectCommand<CinemaHall, Collection<MovieScreening>>{
   private static final long serialVersionUID = 827299192L;
   public CinemaHall_getScreenings_Command(CinemaHall receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getScreenings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
