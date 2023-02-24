/**--- Generated at Fri Feb 24 18:32:14 CET 2023 
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
import java.util.Optional;
import java.sql.ResultSet;
import src.idManagement.IDManagerTransient;
import src.db.executer.PersistenceException;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinema.proxies.IPriceCategoryServiceBox;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class PriceCategoryServiceBox extends PriceCategory implements java.io.Serializable, IPriceCategoryServiceBox
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Optional<PriceCategoryServiceBox> theInstance = Optional.empty();
   private Optional<Integer> PriceCategoryServiceBoxId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private PriceCategoryServiceBox(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, Optional<Integer> PriceCategoryServiceBoxId, boolean objectOnly)
   {
      super(id, priceId, price, objectOnly);
      this.PriceCategoryServiceBoxId = PriceCategoryServiceBoxId;
      if(objectOnly) return;
   }
   private static PriceCategoryServiceBox instantiateRuntimeCopy(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, Optional<Integer> PriceCategoryServiceBoxId){
      return new PriceCategoryServiceBox(id, priceId, price, PriceCategoryServiceBoxId, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static PriceCategoryServiceBox getInstance() throws PersistenceException{
      if(!theInstance.isPresent())theInstance = Optional.of(load());
      return theInstance.get();
   }
   private static PriceCategoryServiceBox load() throws PersistenceException {
      try{
         Integer typeKey = PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "PriceCategoryServiceBox");
         Optional<ResultSet> rs = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter("Cinema").selectEntriesOfTable("PriceCategory", typeKey);
         if(rs.isPresent()) rs.get().next();
         Integer id = rs.isPresent() ? rs.get().getInt("id") : IDManagerTransient.getTheInstance().getNextId();
         Optional<Integer> PriceCategoryServiceBoxId = Optional.empty();
         if(rs.isPresent()) PriceCategoryServiceBoxId = (rs.get().getObject("PriceCategoryServiceBoxId") == null ? Optional.empty() : Optional.of(rs.get().getInt("PriceCategoryServiceBoxId")));
         Optional<Integer> priceId = Optional.empty();
         if(rs.isPresent()) priceId = (rs.get().getObject("priceId") == null ? Optional.empty() : Optional.of(rs.get().getInt("priceId")));
         Optional<baseTypes.Rational> price = Optional.empty();
         if(rs.isPresent()) price = (rs.get().getObject("price") == null ? Optional.empty() : Optional.of(new baseTypes.Rational(rs.get().getString("price"))));
         return PriceCategoryServiceBox.instantiateRuntimeCopy(id, priceId, price, PriceCategoryServiceBoxId);
      } catch (SQLException | NoConnectionException e) {
         throw new PersistenceException(e.getMessage());
      }
   }
   public PriceCategoryServiceBox getTheObject(){
      return this;
   }
   public Optional<Integer> getPriceCategoryServiceBoxId() {
      return this.PriceCategoryServiceBoxId;
   }
   public void setPriceCategoryServiceBoxId(Integer newPriceCategoryServiceBoxId) throws PersistenceException{
      this.PriceCategoryServiceBoxId = Optional.of(newPriceCategoryServiceBoxId);
      try{Cinema.getInstance().getDmlExecuter().update("PriceCategory", "PriceCategoryServiceBoxId", newPriceCategoryServiceBoxId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
