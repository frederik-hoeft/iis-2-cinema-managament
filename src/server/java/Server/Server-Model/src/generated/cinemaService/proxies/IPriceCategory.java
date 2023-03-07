/**--- Generated at Tue Mar 07 14:00:48 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinemaService.PriceCategory;
import java.util.Optional;
public interface IPriceCategory extends Identifiable{
   public PriceCategory getTheObject();
   public Optional<baseTypes.Rational> getPrice() ;
   public void setPrice(baseTypes.Rational newPrice) throws PersistenceException;
}
