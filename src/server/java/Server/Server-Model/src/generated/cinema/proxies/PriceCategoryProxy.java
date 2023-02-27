/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- No Change Allowed!  
 */
package generated.cinema.proxies;
import src.db.executer.PersistenceException;
import generated.cinema.PriceCategory;
import java.util.Optional;
public abstract class PriceCategoryProxy implements IPriceCategory{
   public abstract PriceCategory getTheObject();
   public boolean equals(Object o) {
      if(!(o instanceof IPriceCategory)) return false;
      return ((IPriceCategory)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Optional<Integer> getPriceId() {
      return this.getTheObject().getPriceId();
   }
   public void setPriceId(Integer newPriceId) throws PersistenceException{
      this.getTheObject().setPriceId(newPriceId);
   }
   public Optional<baseTypes.Rational> getPrice() {
      return this.getTheObject().getPrice();
   }
   public void setPrice(baseTypes.Rational newPrice) throws PersistenceException{
      this.getTheObject().setPrice(newPrice);
   }
}
