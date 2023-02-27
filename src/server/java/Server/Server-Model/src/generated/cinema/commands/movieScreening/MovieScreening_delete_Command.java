/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_delete_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = -1270781666L;
   public MovieScreening_delete_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{MovieScreening.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
