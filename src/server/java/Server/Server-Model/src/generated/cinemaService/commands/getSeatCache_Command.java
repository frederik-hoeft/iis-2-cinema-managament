/**--- Generated at Tue Mar 07 13:02:02 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getSeatCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.SeatProxy>>{
   private static final long serialVersionUID = 699635276L;
   public getSeatCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getSeatCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
