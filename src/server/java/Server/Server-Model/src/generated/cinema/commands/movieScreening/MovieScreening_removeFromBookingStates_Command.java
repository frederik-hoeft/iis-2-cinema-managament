/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_removeFromBookingStates_Command extends ObjectCommand<MovieScreening, Boolean>{
   private static final long serialVersionUID = 739029586L;
   private BookingState arg;
   public MovieScreening_removeFromBookingStates_Command(MovieScreening receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromBookingStates(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
