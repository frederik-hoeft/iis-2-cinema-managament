/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class Seat_constructor_Command extends ServiceCommand<Seat>{
   private static final long serialVersionUID = -1373564523L;
   private String  name;
   private SeatRow  row;
   public Seat_constructor_Command(String  name, SeatRow  row){
      super();
      this.name = name;
      this.row = row;
   }
   public void execute(){
      try{this.result = Seat.createFresh(name, row);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public String  name(){return name;}
   public SeatRow  row(){return row;}
}
