/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movie;
import generated.cinema.*;
import commands.*;
public class Movie_delete_Command extends ObjectCommand<Movie, Void>{
   private static final long serialVersionUID = 974314402L;
   public Movie_delete_Command(Movie receiver){
      super(receiver);
   }
   public void execute(){
      try{Movie.delete(receiver.getId());
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
