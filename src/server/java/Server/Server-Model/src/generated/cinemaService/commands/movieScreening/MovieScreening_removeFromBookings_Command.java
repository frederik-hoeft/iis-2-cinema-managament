/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_removeFromBookings_Command extends ObjectCommand<MovieScreening, Boolean>{
   private static final long serialVersionUID = 1003297998L;
   private BookingState arg;
   public MovieScreening_removeFromBookings_Command(MovieScreening receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromBookings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
