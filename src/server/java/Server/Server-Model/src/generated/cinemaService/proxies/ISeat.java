/**--- Generated at Fri Mar 03 01:26:11 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;
public interface ISeat extends Identifiable{
   public Seat getTheObject();
   public Integer getId();
   public SeatRow getRow() throws PersistenceException;
   public void setRow(SeatRow newRow)throws PersistenceException;
}
