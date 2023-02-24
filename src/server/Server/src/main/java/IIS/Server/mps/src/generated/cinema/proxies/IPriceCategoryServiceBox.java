/**--- Generated at Thu Feb 23 23:12:56 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.PriceCategoryServiceBox;
import java.util.Optional;
public interface IPriceCategoryServiceBox extends IPriceCategory{
   public PriceCategoryServiceBox getTheObject();
   public Integer getId();
   public Optional<Integer> getPriceCategoryServiceBoxId() ;
   public void setPriceCategoryServiceBoxId(Integer newPriceCategoryServiceBoxId) throws PersistenceException;
}
