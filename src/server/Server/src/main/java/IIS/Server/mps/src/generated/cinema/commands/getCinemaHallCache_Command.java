/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getCinemaHallCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.CinemaHallProxy>>{
   private static final long serialVersionUID = 1201045228L;
   public getCinemaHallCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getCinemaHallCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
