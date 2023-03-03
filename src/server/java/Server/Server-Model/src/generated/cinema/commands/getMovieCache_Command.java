/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getMovieCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.MovieProxy>>{
   private static final long serialVersionUID = 1544343623L;
   public getMovieCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getMovieCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
