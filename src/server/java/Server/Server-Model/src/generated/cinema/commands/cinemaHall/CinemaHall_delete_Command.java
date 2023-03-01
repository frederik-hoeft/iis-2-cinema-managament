/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.cinemaHall;
import generated.cinema.*;
import commands.*;
public class CinemaHall_delete_Command extends ObjectCommand<CinemaHall, Void>{
   private static final long serialVersionUID = 1786657328L;
   public CinemaHall_delete_Command(CinemaHall receiver){
      super(receiver);
   }
   public void execute(){
      try{CinemaHall.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
