/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getBookingCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.BookingProxy>>{
   private static final long serialVersionUID = 1712152887L;
   public getBookingCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getBookingCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
