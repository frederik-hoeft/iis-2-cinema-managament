/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.commands;
import generated.cinema.*;
import commands.*;
public class Reservation_constructor_Command extends ServiceCommand<Reservation>{
   private static final long serialVersionUID = 102353726L;
   private MovieScreening screening;
   private Seat seat;
   private Customer customer;
   private Integer  reservationId;
   public Reservation_constructor_Command(MovieScreening screening, Seat seat, Customer customer, Integer  reservationId){
      super();
      this.screening = screening;
      this.seat = seat;
      this.customer = customer;
      this.reservationId = reservationId;
   }
   public void execute(){
      try{this.result = Reservation.createFresh(screening, seat, customer, reservationId);
      }catch(Exception e){this.e = e;
      }finally{Cinema.getInstance().notifyObservers(this);}
   }
   public MovieScreening screening(){return screening;}
   public Seat seat(){return seat;}
   public Customer customer(){return customer;}
   public Integer  reservationId(){return reservationId;}
}
