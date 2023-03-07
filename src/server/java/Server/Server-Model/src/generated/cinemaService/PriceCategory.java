/**--- Generated at Tue Mar 07 13:35:39 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinemaService;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceExecuterFactory;
import observation.Observable;
import generated.cinemaService.proxies.IPriceCategory;
import generated.cinemaService.relationControl.*;
import generated.cinemaService.proxies.*;
import src.db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
import exceptions.ConstraintViolation;
import java.util.Optional;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public abstract class PriceCategory extends Observable implements java.io.Serializable, IPriceCategory
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Optional<baseTypes.Rational> price;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   public PriceCategory(Integer id, Optional<baseTypes.Rational> price, boolean objectOnly)
   {
      super();
      this.setId(id);
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
   public Optional<baseTypes.Rational> getPrice() {
      return this.price;
   }
   public void setPrice(baseTypes.Rational newPrice) throws PersistenceException{
      this.price = Optional.of(newPrice);
      try{CinemaService.getInstance().getDmlExecuter().update("PriceCategory", "price", "'" + newPrice.toString() + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
