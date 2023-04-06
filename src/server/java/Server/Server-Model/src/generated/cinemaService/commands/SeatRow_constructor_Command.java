/**--- Generated at Wed Apr 05 19:50:25 CEST 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.commands;
import generated.cinemaService.*;
import commands.*;
public class SeatRow_constructor_Command extends ServiceCommand<SeatRow>{
   private static final long serialVersionUID = -493627445L;
   private PriceCategory price;
   private String  name;
   private CinemaHall  hall;
   public SeatRow_constructor_Command(PriceCategory price, String  name, CinemaHall  hall){
      super();
      this.price = price;
      this.name = name;
      this.hall = hall;
   }
   public void execute(){
      try{this.result = SeatRow.createFresh(price, name, hall);
      }catch(Exception e){this.e = e;
      }finally{CinemaService.getInstance().notifyObservers(this);}
   }
   public PriceCategory price(){return price;}
   public String  name(){return name;}
   public CinemaHall  hall(){return hall;}
}
