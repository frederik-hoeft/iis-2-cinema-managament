/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movie;
import generated.cinema.*;
import commands.*;
public class Movie_addToScreenings_Command extends ObjectCommand<Movie, Void>{
   private static final long serialVersionUID = -431470777L;
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
