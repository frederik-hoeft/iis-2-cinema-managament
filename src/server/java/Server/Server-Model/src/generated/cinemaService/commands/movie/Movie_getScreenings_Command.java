/**--- Generated at Wed Mar 08 17:23:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movie;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class Movie_getScreenings_Command extends ObjectCommand<Movie, Collection<MovieScreening>>{
   private static final long serialVersionUID = -1131995241L;
   public Movie_getScreenings_Command(Movie receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getScreenings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
