/**--- Generated at Tue Mar 07 13:02:02 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Movie_constructor_Command extends ServiceCommand<Movie>{
   private static final long serialVersionUID = -1812413472L;
   private String  title;
   private String  description;
   public Movie_constructor_Command(String  title, String  description){
      super();
      this.title = title;
      this.description = description;
   }
   public void execute(){
      try{this.result = Movie.createFresh(title, description);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public String  title(){return title;}
   public String  description(){return description;}
}
