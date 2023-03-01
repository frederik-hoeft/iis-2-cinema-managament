/**--- Generated at Wed Mar 01 16:05:44 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getReservationCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.ReservationProxy>>{
   private static final long serialVersionUID = 215885446L;
   public getReservationCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getReservationCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
