/**--- Generated at Wed Mar 08 17:23:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movie;
import generated.cinemaService.*;
import commands.*;
public class Movie_removeFromScreenings_Command extends ObjectCommand<Movie, Boolean>{
   private static final long serialVersionUID = -464063140L;
   private MovieScreening arg;
   public Movie_removeFromScreenings_Command(Movie receiver, MovieScreening arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.result = this.receiver.removeFromScreenings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public MovieScreening arg(){return this.arg;}
}
