/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class MovieScreening_getHall_Command extends ObjectCommand<MovieScreening, Collection<CinemaHall>>{
   private static final long serialVersionUID = -1596368083L;
   public MovieScreening_getHall_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getHall();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
