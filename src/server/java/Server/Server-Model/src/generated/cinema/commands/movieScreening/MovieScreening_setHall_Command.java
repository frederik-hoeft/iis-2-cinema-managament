/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands.movieScreening;
import generated.cinema.*;
import commands.*;
public class MovieScreening_setHall_Command extends ObjectCommand<MovieScreening, Void>{
   private static final long serialVersionUID = -746243657L;
   private CinemaHall arg;
   public MovieScreening_setHall_Command(MovieScreening receiver, CinemaHall arg){
      super(receiver);
      this.arg = arg;
   }
   public void execute(){
      try{this.receiver.setHall(arg);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
   public CinemaHall arg(){return this.arg;}
}
