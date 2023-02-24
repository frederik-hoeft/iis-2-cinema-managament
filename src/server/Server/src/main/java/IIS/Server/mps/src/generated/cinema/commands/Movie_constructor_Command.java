/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Movie_constructor_Command extends ServiceCommand<Movie>{
   private static final long serialVersionUID = -558118163L;
   private Integer  movieId;
   private String  title;
   public Movie_constructor_Command(Integer  movieId, String  title){
      super();
      this.movieId = movieId;
      this.title = title;
   }
   public void execute(){
      try{this.result = Movie.createFresh(movieId, title);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public Integer  movieId(){return movieId;}
   public String  title(){return title;}
}
