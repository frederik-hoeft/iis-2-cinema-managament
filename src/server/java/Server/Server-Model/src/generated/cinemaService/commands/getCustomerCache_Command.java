/**--- Generated at Tue Mar 07 22:26:21 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getCustomerCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.CustomerProxy>>{
   private static final long serialVersionUID = -446874854L;
   public getCustomerCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getCustomerCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
