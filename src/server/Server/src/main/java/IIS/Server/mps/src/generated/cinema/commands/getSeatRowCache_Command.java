/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getSeatRowCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.SeatRowProxy>>{
   private static final long serialVersionUID = -1773992397L;
   public getSeatRowCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getSeatRowCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
