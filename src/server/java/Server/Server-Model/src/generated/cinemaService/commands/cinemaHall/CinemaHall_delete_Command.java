/**--- Generated at Wed Mar 08 00:30:18 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.cinemaHall;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_delete_Command extends ObjectCommand<CinemaHall, Void>{
   private static final long serialVersionUID = 606016608L;
   public CinemaHall_delete_Command(CinemaHall receiver){
      super(receiver);
   }
   public void execute(){
      try{CinemaHall.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
