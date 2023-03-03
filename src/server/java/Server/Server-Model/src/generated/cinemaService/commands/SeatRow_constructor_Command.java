/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_constructor_Command extends ServiceCommand<SeatRow>{
   private static final long serialVersionUID = 1068139627L;
   private CinemaHall hall;
   private PriceCategory price;
   public SeatRow_constructor_Command(CinemaHall hall, PriceCategory price){
      super();
      this.hall = hall;
      this.price = price;
   }
   public void execute(){
      try{this.result = SeatRow.createFresh(hall, price);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public CinemaHall hall(){return hall;}
   public PriceCategory price(){return price;}
}
