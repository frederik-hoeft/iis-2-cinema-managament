/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class getMovieCache_Command extends ServiceCommand<java.util.Map<Integer, generated.cinema.proxies.MovieProxy>>{
   private static final long serialVersionUID = 2063493860L;
   public getMovieCache_Command(){
      super();
   }
   public void execute(){
      try{this.result = Cinema.getInstance().getMovieCache();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
