/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getReservationCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.ReservationProxy>>{
   private static final long serialVersionUID = -500802155L;
   public getReservationCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getReservationCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
