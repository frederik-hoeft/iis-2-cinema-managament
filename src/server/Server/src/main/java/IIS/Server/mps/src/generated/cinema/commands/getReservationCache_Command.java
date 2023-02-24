/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getReservationCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.ReservationProxy>>{
   private static final long serialVersionUID = 938111033L;
   public getReservationCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getReservationCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
