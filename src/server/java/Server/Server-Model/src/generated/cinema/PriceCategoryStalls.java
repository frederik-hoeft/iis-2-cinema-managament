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
import generated.cinema.proxies.IPriceCategoryStalls;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class PriceCategoryStalls extends PriceCategory implements java.io.Serializable, IPriceCategoryStalls
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Optional<PriceCategoryStalls> theInstance = Optional.empty();
   private Optional<Integer> PriceCategoryStallsId;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private PriceCategoryStalls(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, Optional<Integer> PriceCategoryStallsId, boolean objectOnly)
   {
      super(id, priceId, price, objectOnly);
      this.PriceCategoryStallsId = PriceCategoryStallsId;
      if(objectOnly) return;
   }
   private static PriceCategoryStalls instantiateRuntimeCopy(Integer id, Optional<Integer> priceId, Optional<baseTypes.Rational> price, Optional<Integer> PriceCategoryStallsId){
      return new PriceCategoryStalls(id, priceId, price, PriceCategoryStallsId, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static PriceCategoryStalls getInstance() throws PersistenceException{
      if(!theInstance.isPresent())theInstance = Optional.of(load());
      return theInstance.get();
   }
   private static PriceCategoryStalls load() throws PersistenceException {
      try{
         Integer typeKey = PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("Cinema", "PriceCategoryStalls");
         Optional<ResultSet> rs = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter("Cinema").selectEntriesOfTable("PriceCategory", typeKey);
         if(rs.isPresent()) rs.get().next();
         Integer id = rs.isPresent() ? rs.get().getInt("id") : IDManagerTransient.getTheInstance().getNextId();
         Optional<Integer> PriceCategoryStallsId = Optional.empty();
         if(rs.isPresent()) PriceCategoryStallsId = (rs.get().getObject("PriceCategoryStallsId") == null ? Optional.empty() : Optional.of(rs.get().getInt("PriceCategoryStallsId")));
         Optional<Integer> priceId = Optional.empty();
         if(rs.isPresent()) priceId = (rs.get().getObject("priceId") == null ? Optional.empty() : Optional.of(rs.get().getInt("priceId")));
         Optional<baseTypes.Rational> price = Optional.empty();
         if(rs.isPresent()) price = (rs.get().getObject("price") == null ? Optional.empty() : Optional.of(new baseTypes.Rational(rs.get().getString("price"))));
         return PriceCategoryStalls.instantiateRuntimeCopy(id, priceId, price, PriceCategoryStallsId);
      } catch (SQLException | NoConnectionException e) {
         throw new PersistenceException(e.getMessage());
      }
   }
   public PriceCategoryStalls getTheObject(){
      return this;
   }
   public Optional<Integer> getPriceCategoryStallsId() {
      return this.PriceCategoryStallsId;
   }
   public void setPriceCategoryStallsId(Integer newPriceCategoryStallsId) throws PersistenceException{
      this.PriceCategoryStallsId = Optional.of(newPriceCategoryStallsId);
      try{Cinema.getInstance().getDmlExecuter().update("PriceCategory", "PriceCategoryStallsId", newPriceCategoryStallsId.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
