/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_addToRows_Command extends ObjectCommand<CinemaHall, Void>{
   private static final long serialVersionUID = -1524945165L;
   private SeatRow arg;
   public CinemaHall_addToRows_Command(CinemaHall receiver, SeatRow arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToRows(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public SeatRow arg(){return this.arg;}
}
