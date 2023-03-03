/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_constructor_Command extends ServiceCommand<CinemaHall>{
   private static final long serialVersionUID = -1983814272L;
   private Boolean  available;
   public CinemaHall_constructor_Command(Boolean  available){
      super();
      this.available = available;
   }
   public void execute(){
      try{this.result = CinemaHall.createFresh(available);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public Boolean  available(){return available;}
}
