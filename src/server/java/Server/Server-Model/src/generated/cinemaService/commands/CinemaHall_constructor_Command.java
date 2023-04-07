/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class CinemaHall_constructor_Command extends ServiceCommand<CinemaHall>{
   private static final long serialVersionUID = 1374511838L;
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
