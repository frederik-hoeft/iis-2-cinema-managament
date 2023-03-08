/**--- Generated at Wed Mar 08 17:23:05 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import generated.cinemaService.PriceCategory;
public abstract class PriceCategoryProxy implements IPriceCategory{
   public abstract PriceCategory getTheObject();
   public boolean equals(Object o) {
      if(!(o instanceof IPriceCategory)) return false;
      return ((IPriceCategory)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
}
