/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getCustomerCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.CustomerProxy>>{
   private static final long serialVersionUID = -1547428221L;
   public getCustomerCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getCustomerCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
