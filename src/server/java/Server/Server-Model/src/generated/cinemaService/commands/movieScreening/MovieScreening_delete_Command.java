/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movieScreening;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_delete_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = -1599529869L;
   public MovieScreening_delete_Command(MovieScreening receiver){
      super(receiver);
   }
   public void execute(){
      try{MovieScreening.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}