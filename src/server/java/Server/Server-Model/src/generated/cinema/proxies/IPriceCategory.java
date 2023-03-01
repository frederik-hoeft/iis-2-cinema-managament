/**--- Generated at Wed Mar 01 16:05:46 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.PriceCategory;
import java.util.Optional;
public interface IPriceCategory extends Identifiable{
   public PriceCategory getTheObject();
   public Optional<Integer> getPriceId() ;
   public void setPriceId(Integer newPriceId) throws PersistenceException;
   public Optional<baseTypes.Rational> getPrice() ;
   public void setPrice(baseTypes.Rational newPrice) throws PersistenceException;
}
