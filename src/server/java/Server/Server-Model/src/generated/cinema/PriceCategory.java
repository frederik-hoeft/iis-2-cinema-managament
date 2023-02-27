/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- Mode = No Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinema;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import observation.Observable;
import generated.cinema.proxies.IPriceCategory;
import java.util.Optional;
import src.db.executer.PersistenceException;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public abstract class PriceCategory extends Observable implements java.io.Serializable, IPriceCategory
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Optional<Integer> priceId;
   private Optional<baseTypes.Rational> price;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   public PriceCategory(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.priceId = priceId;
      this.price = price;
      if(objectOnly) return;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public PriceCategory getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IPriceCategory)) return false;
      return ((IPriceCategory)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Optional<Integer> getPriceId() {
      return this.priceId;
   }
   public void setPriceId(Integer newPriceId) throws PersistenceException{
      this.priceId = Optional.of(newPriceId);
      try{Cinema.getInstance().getDmlExecuter().update("PriceCategory", "priceId", newPriceId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Optional<baseTypes.Rational> getPrice() {
      return this.price;
   }
   public void setPrice(baseTypes.Rational newPrice) throws PersistenceException{
      this.price = Optional.of(newPrice);
      try{Cinema.getInstance().getDmlExecuter().update("PriceCategory", "price", "'" + newPrice.toString() + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
