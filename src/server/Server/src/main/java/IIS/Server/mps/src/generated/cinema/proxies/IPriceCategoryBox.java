/**--- Generated at Fri Feb 24 16:52:36 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.PriceCategoryBox;
import java.util.Optional;
public interface IPriceCategoryBox extends IPriceCategory{
   public PriceCategoryBox getTheObject();
   public Integer getId();
   public Optional<Integer> getPriceCategoryBoxId() ;
   public void setPriceCategoryBoxId(Integer newPriceCategoryBoxId) throws PersistenceException;
}
