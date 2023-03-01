/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class MovieScreening_constructor_Command extends ServiceCommand<MovieScreening>{
   private static final long serialVersionUID = -1906953909L;
   private Movie movie;
   private CinemaHall hall;
   private Integer  movieScreeningId;
   private Boolean  finished;
   public MovieScreening_constructor_Command(Movie movie, CinemaHall hall, Integer  movieScreeningId, Boolean  finished){
      super();
      this.movie = movie;
      this.hall = hall;
      this.movieScreeningId = movieScreeningId;
      this.finished = finished;
   }
   public void execute(){
      try{this.result = MovieScreening.createFresh(movie, hall, movieScreeningId, finished);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public Movie movie(){return movie;}
   public CinemaHall hall(){return hall;}
   public Integer  movieScreeningId(){return movieScreeningId;}
   public Boolean  finished(){return finished;}
}
