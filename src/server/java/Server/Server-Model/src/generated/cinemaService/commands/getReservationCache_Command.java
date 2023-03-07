/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getReservationCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.ReservationProxy>>{
   private static final long serialVersionUID = -812492843L;
   public getReservationCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getReservationCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
