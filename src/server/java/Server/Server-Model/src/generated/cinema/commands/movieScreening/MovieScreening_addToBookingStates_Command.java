/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_addToBookingStates_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = -955838100L;
   private BookingState arg;
   public MovieScreening_addToBookingStates_Command(MovieScreening receiver, BookingState arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToBookingStates(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public BookingState arg(){return this.arg;}
}
