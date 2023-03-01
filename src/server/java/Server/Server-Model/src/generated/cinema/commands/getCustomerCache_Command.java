/**--- Generated at Wed Mar 01 16:05:44 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getCustomerCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.CustomerProxy>>{
   private static final long serialVersionUID = 1353781550L;
   public getCustomerCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getCustomerCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
