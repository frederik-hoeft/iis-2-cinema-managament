/**--- Generated at Tue Mar 07 22:26:21 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class getMovieScreeningCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinemaService.proxies.MovieScreeningProxy>>{
   private static final long serialVersionUID = 2066640209L;
   public getMovieScreeningCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = CinemaService.getInstance().getMovieScreeningCache();
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
