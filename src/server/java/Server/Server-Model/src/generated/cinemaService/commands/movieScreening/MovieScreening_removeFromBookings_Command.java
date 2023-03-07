/**--- Generated at Tue Mar 07 22:26:21 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_removeFromBookings_Command extends ObjectCommand<MovieScreening, Boolean>{
   private static final long serialVersionUID = 1355484503L;
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
