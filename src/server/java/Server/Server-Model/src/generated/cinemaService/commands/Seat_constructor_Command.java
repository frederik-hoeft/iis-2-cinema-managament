/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Seat_constructor_Command extends ServiceCommand<Seat>{
   private static final long serialVersionUID = -58546476L;
   private SeatRow row;
   public Seat_constructor_Command(SeatRow row){
      super();
      this.row = row;
   }
   public void execute(){
      try{this.result = Seat.createFresh(row);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public SeatRow row(){return row;}
}
