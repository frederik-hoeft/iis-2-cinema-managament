/**--- Generated at Tue Mar 07 13:35:37 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_getHall_Command extends ObjectCommand<MovieScreening, CinemaHall>{
   private static final long serialVersionUID = -666640145L;
   public MovieScreening_getHall_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getHall();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
