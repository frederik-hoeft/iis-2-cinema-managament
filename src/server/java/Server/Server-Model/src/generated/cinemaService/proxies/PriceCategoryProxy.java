/**--- Generated at Tue Mar 07 13:02:04 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinemaService.proxies;
import src.db.executer.PersistenceException;
import generated.cinemaService.PriceCategory;
import java.util.Optional;
public abstract class PriceCategoryProxy implements IPriceCategory{
   public abstract PriceCategory getTheObject();
   public boolean equals(Object o) {
      if(!(o instanceof IPriceCategory)) return false;
      return ((IPriceCategory)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Optional<baseTypes.Rational> getPrice() {
      return this.getTheObject().getPrice();
   }
   public void setPrice(baseTypes.Rational newPrice) throws PersistenceException{
      this.getTheObject().setPrice(newPrice);
   }
}
