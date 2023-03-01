/**--- Generated at Wed Mar 01 16:05:44 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getCinemaHallCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.CinemaHallProxy>>{
   private static final long serialVersionUID = 893923196L;
   public getCinemaHallCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getCinemaHallCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
