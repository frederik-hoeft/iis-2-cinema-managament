/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Seat_constructor_Command extends ServiceCommand<Seat>{
   private static final long serialVersionUID = 849261443L;
   private SeatRow row;
   private String  name;
   public Seat_constructor_Command(SeatRow row, String  name){
      super();
      this.row = row;
      this.name = name;
   }
   public void execute(){
      try{this.result = Seat.createFresh(row, name);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public SeatRow row(){return row;}
   public String  name(){return name;}
}
