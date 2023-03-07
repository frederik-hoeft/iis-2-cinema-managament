/**--- Generated at Tue Mar 07 13:29:05 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_removeFromBookingStates_Command extends ObjectCommand<MovieScreening, Boolean>{
   private static final long serialVersionUID = 1626105671L;
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
