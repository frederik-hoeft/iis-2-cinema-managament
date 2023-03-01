/**--- Generated at Wed Mar 01 16:05:45 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class SeatRow_constructor_Command extends ServiceCommand<SeatRow>{
   private static final long serialVersionUID = -355573517L;
   private CinemaHall hall;
   private PriceCategory price;
   private Integer  rowId;
   public SeatRow_constructor_Command(CinemaHall hall, PriceCategory price, Integer  rowId){
      super();
      this.hall = hall;
      this.price = price;
      this.rowId = rowId;
   }
   public void execute(){
      try{this.result = SeatRow.createFresh(hall, price, rowId);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public CinemaHall hall(){return hall;}
   public PriceCategory price(){return price;}
   public Integer  rowId(){return rowId;}
}
