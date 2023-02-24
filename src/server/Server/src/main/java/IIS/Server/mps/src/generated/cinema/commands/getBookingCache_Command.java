/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getBookingCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.BookingProxy>>{
   private static final long serialVersionUID = 664766297L;
   public getBookingCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getBookingCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
