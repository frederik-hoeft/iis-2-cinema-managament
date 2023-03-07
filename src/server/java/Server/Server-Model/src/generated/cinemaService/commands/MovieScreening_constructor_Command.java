/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_constructor_Command extends ServiceCommand<MovieScreening>{
   private static final long serialVersionUID = 1134891438L;
   private Boolean  finished;
   private String  name;
   private Movie  movie;
   private CinemaHall  hall;
   public MovieScreening_constructor_Command(Boolean  finished, String  name, Movie  movie, CinemaHall  hall){
      super();
      this.finished = finished;
      this.name = name;
      this.movie = movie;
      this.hall = hall;
   }
   public void execute(){
      try{this.result = MovieScreening.createFresh(finished, name, movie, hall);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public Boolean  finished(){return finished;}
   public String  name(){return name;}
   public Movie  movie(){return movie;}
   public CinemaHall  hall(){return hall;}
}
