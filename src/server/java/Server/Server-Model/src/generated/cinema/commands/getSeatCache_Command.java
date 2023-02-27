/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getSeatCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.SeatProxy>>{
   private static final long serialVersionUID = 1279093112L;
   public getSeatCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getSeatCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
