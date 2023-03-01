/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class CinemaHall_constructor_Command extends ServiceCommand<CinemaHall>{
   private static final long serialVersionUID = 1583176298L;
   private Boolean  available;
   private Integer  roomId;
   public CinemaHall_constructor_Command(Boolean  available, Integer  roomId){
      super();
      this.available = available;
      this.roomId = roomId;
   }
   public void execute(){
      try{this.result = CinemaHall.createFresh(available, roomId);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public Boolean  available(){return available;}
   public Integer  roomId(){return roomId;}
}
