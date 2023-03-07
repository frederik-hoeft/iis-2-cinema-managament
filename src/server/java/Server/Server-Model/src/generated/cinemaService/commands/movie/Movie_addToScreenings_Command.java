/**--- Generated at Tue Mar 07 13:02:02 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands.movie;
import generated.cinemaService.*;
import commands.*;
public class Movie_addToScreenings_Command extends ObjectCommand<Movie, Void>{
   private static final long serialVersionUID = -958267535L;
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
