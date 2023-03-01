/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Seat_constructor_Command extends ServiceCommand<Seat>{
   private static final long serialVersionUID = 940587679L;
   private SeatRow row;
   private Integer  seatId;
   public Seat_constructor_Command(SeatRow row, Integer  seatId){
      super();
      this.row = row;
      this.seatId = seatId;
   }
   public void execute(){
      try{this.result = Seat.createFresh(row, seatId);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public SeatRow row(){return row;}
   public Integer  seatId(){return seatId;}
}
