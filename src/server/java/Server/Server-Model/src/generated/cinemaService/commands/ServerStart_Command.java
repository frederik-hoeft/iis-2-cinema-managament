/**--- Generated at Tue Mar 07 13:02:02 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class ServerStart_Command extends ServiceCommand<Void>{
   private static final long serialVersionUID = -441908805L;
   public ServerStart_Command(){
      super();
   }
   public void execute(){
      try{CinemaService.getInstance();}catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
}
