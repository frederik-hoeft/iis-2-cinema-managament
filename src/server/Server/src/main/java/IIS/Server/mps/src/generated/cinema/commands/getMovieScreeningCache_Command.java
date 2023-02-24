/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getMovieScreeningCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.MovieScreeningProxy>>{
   private static final long serialVersionUID = 1294879205L;
   public getMovieScreeningCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getMovieScreeningCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
