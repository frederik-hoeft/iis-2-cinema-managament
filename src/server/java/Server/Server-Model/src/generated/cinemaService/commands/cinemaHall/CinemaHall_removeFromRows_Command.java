/**--- Generated at Tue Mar 07 13:29:06 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_removeFromRows_Command extends ObjectCommand<CinemaHall, Boolean>{
   private static final long serialVersionUID = 1091881667L;
   private SeatRow arg;
   public CinemaHall_removeFromRows_Command(CinemaHall receiver, SeatRow arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromRows(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public SeatRow arg(){return this.arg;}
}
