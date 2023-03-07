/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_getMovie_Command extends ObjectCommand<MovieScreening, Movie>{
   private static final long serialVersionUID = -204204852L;
   public MovieScreening_getMovie_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getMovie();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
