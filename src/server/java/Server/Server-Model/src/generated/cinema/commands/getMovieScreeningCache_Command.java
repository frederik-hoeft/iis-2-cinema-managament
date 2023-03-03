/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getMovieScreeningCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.MovieScreeningProxy>>{
   private static final long serialVersionUID = 1794030466L;
   public getMovieScreeningCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getMovieScreeningCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
