/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_removeFromScreenings_Command extends ObjectCommand<CinemaHall, Boolean>{
   private static final long serialVersionUID = 780791531L;
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
