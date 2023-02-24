/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_setMovie_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = 1126626265L;
   private Movie arg;
   public MovieScreening_setMovie_Command(MovieScreening receiver, Movie arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setMovie(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public Movie arg(){return this.arg;}
}
