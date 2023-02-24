/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.bookingState;
import generated.cinema.*;
import commands.*;
public class BookingState_setScreening_Command extends ObjectCommand<BookingState, Void>{
   private static final long serialVersionUID = -1513365170L;
   private MovieScreening arg;
   public BookingState_setScreening_Command(BookingState receiver, MovieScreening arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setScreening(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public MovieScreening arg(){return this.arg;}
}
