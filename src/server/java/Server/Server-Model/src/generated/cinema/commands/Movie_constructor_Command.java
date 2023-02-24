/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Movie_constructor_Command extends ServiceCommand<Movie>{
   private static final long serialVersionUID = 1425494215L;
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
