/**--- Generated at Wed Apr 05 19:50:24 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getCinemaHallCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.CinemaHallProxy>>{
   private static final long serialVersionUID = -31909491L;
   public getCinemaHallCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getCinemaHallCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
