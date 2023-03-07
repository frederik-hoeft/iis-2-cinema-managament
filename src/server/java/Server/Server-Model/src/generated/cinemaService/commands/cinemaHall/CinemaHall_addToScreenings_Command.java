/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_addToScreenings_Command extends ObjectCommand<CinemaHall, Void>{
   private static final long serialVersionUID = -1444374607L;
   private MovieScreening arg;
   public CinemaHall_addToScreenings_Command(CinemaHall receiver, MovieScreening arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToScreenings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public MovieScreening arg(){return this.arg;}
}
