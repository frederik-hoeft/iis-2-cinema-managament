/**--- Generated at Wed Mar 01 16:05:44 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class ServerStart_Command extends ServiceCommand<Void>{
   private static final long serialVersionUID = -528702099L;
   public ServerStart_Command(){
      super();
   }
   public void execute(){
      try{Cinema.getInstance();}catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
