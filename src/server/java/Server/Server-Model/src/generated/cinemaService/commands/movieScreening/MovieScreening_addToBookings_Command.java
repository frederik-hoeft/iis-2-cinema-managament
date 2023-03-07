/**--- Generated at Tue Mar 07 22:26:21 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_addToBookings_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = 598749846L;
   private BookingState arg;
   public MovieScreening_addToBookings_Command(MovieScreening receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToBookings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
