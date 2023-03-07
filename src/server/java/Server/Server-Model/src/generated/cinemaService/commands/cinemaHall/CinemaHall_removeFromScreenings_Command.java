/**--- Generated at Tue Mar 07 22:26:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_removeFromScreenings_Command extends ObjectCommand<CinemaHall, Boolean>{
   private static final long serialVersionUID = 547435728L;
   private MovieScreening arg;
   public CinemaHall_removeFromScreenings_Command(CinemaHall receiver, MovieScreening arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromScreenings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public MovieScreening arg(){return this.arg;}
}
