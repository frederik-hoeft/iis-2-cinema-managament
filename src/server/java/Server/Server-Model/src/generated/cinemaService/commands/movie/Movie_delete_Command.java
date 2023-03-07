/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movie;
import generated.cinemaService.*;
import commands.*;
public class Movie_delete_Command extends ObjectCommand<Movie, Void>{
   private static final long serialVersionUID = -37629022L;
   public Movie_delete_Command(Movie receiver){
      super(receiver);
   }
   public void execute(){
      try{Movie.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
