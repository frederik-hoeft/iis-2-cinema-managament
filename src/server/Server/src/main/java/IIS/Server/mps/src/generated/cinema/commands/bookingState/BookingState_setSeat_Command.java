/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.bookingState;
import generated.cinema.*;
import commands.*;
public class BookingState_setSeat_Command extends ObjectCommand<BookingState, Void>{
   private static final long serialVersionUID = 1996064291L;
   private Seat arg;
   public BookingState_setSeat_Command(BookingState receiver, Seat arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setSeat(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public Seat arg(){return this.arg;}
}
