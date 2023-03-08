/**--- Generated at Wed Mar 08 17:23:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_delete_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = -366574216L;
   public MovieScreening_delete_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{MovieScreening.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
