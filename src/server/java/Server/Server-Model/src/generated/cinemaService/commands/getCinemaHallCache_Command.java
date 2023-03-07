/**--- Generated at Wed Mar 08 00:30:17 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getCinemaHallCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.CinemaHallProxy>>{
   private static final long serialVersionUID = 1586612264L;
   public getCinemaHallCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getCinemaHallCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
