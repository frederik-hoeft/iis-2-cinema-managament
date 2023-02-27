/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
import java.util.Collection;
public class MovieScreening_getBookingStates_Command extends ObjectCommand<MovieScreening, Collection<BookingState>>{
   private static final long serialVersionUID = 36111195L;
   public MovieScreening_getBookingStates_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getBookingStates();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
