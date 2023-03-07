/**--- Generated at Tue Mar 07 22:26:21 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getReservationCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.ReservationProxy>>{
   private static final long serialVersionUID = 1723399091L;
   public getReservationCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getReservationCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
