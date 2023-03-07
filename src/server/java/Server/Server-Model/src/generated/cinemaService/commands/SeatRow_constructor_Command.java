/**--- Generated at Tue Mar 07 13:02:03 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_constructor_Command extends ServiceCommand<SeatRow>{
   private static final long serialVersionUID = -582266101L;
   private CinemaHall hall;
   private PriceCategory price;
   private String  name;
   public SeatRow_constructor_Command(CinemaHall hall, PriceCategory price, String  name){
      super();
      this.hall = hall;
      this.price = price;
      this.name = name;
   }
   public void execute(){
      try{this.result = SeatRow.createFresh(hall, price, name);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public CinemaHall hall(){return hall;}
   public PriceCategory price(){return price;}
   public String  name(){return name;}
}
