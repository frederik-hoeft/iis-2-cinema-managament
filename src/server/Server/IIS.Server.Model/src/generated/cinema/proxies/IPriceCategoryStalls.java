/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.idManagement.Identifiable;
import src.db.executer.PersistenceException;
import generated.cinema.PriceCategoryStalls;
import java.util.Optional;
public interface IPriceCategoryStalls extends IPriceCategory{
   public PriceCategoryStalls getTheObject();
   public Integer getId();
   public Optional<Integer> getPriceCategoryStallsId() ;
   public void setPriceCategoryStallsId(Integer newPriceCategoryStallsId) throws PersistenceException;
}
