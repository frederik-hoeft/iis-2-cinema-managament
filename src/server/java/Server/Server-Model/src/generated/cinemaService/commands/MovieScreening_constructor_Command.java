/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class MovieScreening_constructor_Command extends ServiceCommand<MovieScreening>{
   private static final long serialVersionUID = 2037339136L;
   private Movie movie;
   private CinemaHall hall;
   private Boolean  finished;
   private String  Name;
   public MovieScreening_constructor_Command(Movie movie, CinemaHall hall, Boolean  finished, String  Name){
      super();
      this.movie = movie;
      this.hall = hall;
      this.finished = finished;
      this.Name = Name;
   }
   public void execute(){
      try{this.result = MovieScreening.createFresh(movie, hall, finished, Name);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public Movie movie(){return movie;}
   public CinemaHall hall(){return hall;}
   public Boolean  finished(){return finished;}
   public String  Name(){return Name;}
}
