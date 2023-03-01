/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.cinemaHall;
import generated.cinema.*;
import commands.*;
public class CinemaHall_addToRows_Command extends ObjectCommand<CinemaHall, Void>{
   private static final long serialVersionUID = -477242830L;
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
