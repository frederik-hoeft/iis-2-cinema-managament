/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_delete_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = 376878759L;
   public MovieScreening_delete_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{MovieScreening.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
