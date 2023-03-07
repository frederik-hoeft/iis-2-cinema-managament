/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getSeatRowCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.SeatRowProxy>>{
   private static final long serialVersionUID = 702386310L;
   public getSeatRowCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getSeatRowCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
