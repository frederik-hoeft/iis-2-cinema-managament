/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
import java.util.Collection;
public class MovieScreening_getBookings_Command extends ObjectCommand<MovieScreening, Collection<BookingState>>{
   private static final long serialVersionUID = 1022769901L;
   public MovieScreening_getBookings_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getBookings();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
