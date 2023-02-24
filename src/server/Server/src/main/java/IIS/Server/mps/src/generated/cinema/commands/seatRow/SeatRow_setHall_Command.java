/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.seatRow;
import generated.cinema.*;
import commands.*;
public class SeatRow_setHall_Command extends ObjectCommand<SeatRow, Void>{
   private static final long serialVersionUID = 1036348620L;
   private CinemaHall arg;
   public SeatRow_setHall_Command(SeatRow receiver, CinemaHall arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setHall(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public CinemaHall arg(){return this.arg;}
}
