/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class closeDBConnection_Command extends ServiceCommand<Void>{
   private static final long serialVersionUID = -1167186891L;
   public closeDBConnection_Command(){
      super();
   }
   public void execute(){
      try{Cinema.getInstance().closeDBConnection();
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
}
