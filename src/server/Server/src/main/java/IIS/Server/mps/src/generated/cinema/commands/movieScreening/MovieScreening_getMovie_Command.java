/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_getMovie_Command extends ObjectCommand<MovieScreening, Movie>{
   private static final long serialVersionUID = -554738125L;
   public MovieScreening_getMovie_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getMovie();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}