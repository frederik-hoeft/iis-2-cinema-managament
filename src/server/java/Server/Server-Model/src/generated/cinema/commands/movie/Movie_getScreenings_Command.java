/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movie;
import generated.cinema.*;
import commands.*;
import java.util.Collection;
public class Movie_getScreenings_Command extends ObjectCommand<Movie, Collection<MovieScreening>>{
   private static final long serialVersionUID = -2058441300L;
   public Movie_getScreenings_Command(Movie receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getScreenings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
