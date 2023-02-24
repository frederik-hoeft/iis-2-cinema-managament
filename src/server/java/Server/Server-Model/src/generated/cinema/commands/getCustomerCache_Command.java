/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getCustomerCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.CustomerProxy>>{
   private static final long serialVersionUID = -1407206974L;
   public getCustomerCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getCustomerCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
