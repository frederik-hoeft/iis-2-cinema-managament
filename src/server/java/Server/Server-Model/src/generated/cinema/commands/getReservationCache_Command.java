/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getReservationCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.ReservationProxy>>{
   private static final long serialVersionUID = -1351599342L;
   public getReservationCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getReservationCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
