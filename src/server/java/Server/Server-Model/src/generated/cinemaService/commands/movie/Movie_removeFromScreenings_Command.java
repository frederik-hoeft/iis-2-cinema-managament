/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movie;
import generated.cinemaService.*;
import commands.*;
public class Movie_removeFromScreenings_Command extends ObjectCommand<Movie, Boolean>{
   private static final long serialVersionUID = -1309324234L;
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
