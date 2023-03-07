/**--- Generated at Tue Mar 07 13:35:38 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_constructor_Command extends ServiceCommand<CinemaHall>{
   private static final long serialVersionUID = 1026136686L;
   private Boolean  available;
   private String  name;
   public CinemaHall_constructor_Command(Boolean  available, String  name){
      super();
      this.available = available;
      this.name = name;
   }
   public void execute(){
      try{this.result = CinemaHall.createFresh(available, name);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public Boolean  available(){return available;}
   public String  name(){return name;}
}
