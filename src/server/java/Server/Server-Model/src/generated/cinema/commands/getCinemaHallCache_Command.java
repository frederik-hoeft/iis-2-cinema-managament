/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getCinemaHallCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.CinemaHallProxy>>{
   private static final long serialVersionUID = 732016463L;
   public getCinemaHallCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getCinemaHallCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
