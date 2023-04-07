/**--- Generated at Wed Apr 05 19:50:24 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movie;
import generated.cinemaService.*;
import commands.*;
public class Movie_addToScreenings_Command extends ObjectCommand<Movie, Void>{
   private static final long serialVersionUID = -623907711L;
   private MovieScreening arg;
   public Movie_addToScreenings_Command(Movie receiver, MovieScreening arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.addToScreenings(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public MovieScreening arg(){return this.arg;}
}
