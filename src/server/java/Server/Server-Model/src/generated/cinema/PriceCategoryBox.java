/**--- Generated at Fri Mar 03 01:14:19 CET 2023 
 * --- Mode = Integrated Database 
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
import generated.cinema.proxies.IPriceCategoryBox;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class PriceCategoryBox extends PriceCategory implements java.io.Serializable, IPriceCategoryBox
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Optional<PriceCategoryBox> theInstance = Optional.empty();
   private Optional<Integer> priceCategoryBoxId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private PriceCategoryBox(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, Optional<Integer> priceCategoryBoxId, boolean objectOnly)
   {
      super(id, priceId, price, objectOnly);
      this.priceCategoryBoxId = priceCategoryBoxId;
      if(objectOnly) return;
   }
   private static PriceCategoryBox instantiateRuntimeCopy(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, Optional<Integer> priceCategoryBoxId){
      return new PriceCategoryBox(id, priceId, price, priceCategoryBoxId, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static PriceCategoryBox getInstance() throws PersistenceException{
      if(!theInstance.isPresent())theInstance = Optional.of(load());
      return theInstance.get();
   }
   private static PriceCategoryBox load() throws PersistenceException {
      try{
         Integer typeKey = PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "PriceCategoryBox");
         Optional<ResultSet> rs = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter("Cinema").selectEntriesOfTable("PriceCategory", typeKey);
         if(rs.isPresent()) rs.get().next();
         Integer id = rs.isPresent() ? rs.get().getInt("id") : IDManagerTransient.getTheInstance().getNextId();
         Optional<Integer> priceCategoryBoxId = Optional.empty();
         if(rs.isPresent()) priceCategoryBoxId = (rs.get().getObject("priceCategoryBoxId") == null ? Optional.empty() : Optional.of(rs.get().getInt("priceCategoryBoxId")));
         Optional<Integer> priceId = Optional.empty();
         if(rs.isPresent()) priceId = (rs.get().getObject("priceId") == null ? Optional.empty() : Optional.of(rs.get().getInt("priceId")));
         Optional<baseTypes.Rational> price = Optional.empty();
         if(rs.isPresent()) price = (rs.get().getObject("price") == null ? Optional.empty() : Optional.of(new baseTypes.Rational(rs.get().getString("price"))));
         return PriceCategoryBox.instantiateRuntimeCopy(id, priceId, price, priceCategoryBoxId);
      } catch (SQLException | NoConnectionException e) {
         throw new PersistenceException(e.getMessage());
      }
   }
   public PriceCategoryBox getTheObject(){
      return this;
   }
   public Optional<Integer> getPriceCategoryBoxId() {
      return this.priceCategoryBoxId;
   }
   public void setPriceCategoryBoxId(Integer newPriceCategoryBoxId) throws PersistenceException{
      this.priceCategoryBoxId = Optional.of(newPriceCategoryBoxId);
      try{Cinema.getInstance().getDmlExecuter().update("PriceCategory", "priceCategoryBoxId", newPriceCategoryBoxId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
