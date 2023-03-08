/**--- Generated at Wed Mar 08 17:23:05 CET 2023 
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
import java.util.Optional;
import java.sql.ResultSet;
import src.idManagement.IDManagerTransient;
import src.db.executer.PersistenceException;
import src.db.executer.PersistenceExecuterFactory;
import generated.cinemaService.proxies.IPriceCategoryBox;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class PriceCategoryBox extends PriceCategory implements java.io.Serializable, IPriceCategoryBox
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Optional<PriceCategoryBox> theInstance = Optional.empty();
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private PriceCategoryBox(Integer id, boolean objectOnly)
   {
      super(id, objectOnly);
      if(objectOnly) return;
   }
   private static PriceCategoryBox instantiateRuntimeCopy(Integer id){
      return new PriceCategoryBox(id, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static PriceCategoryBox getInstance() throws PersistenceException{
      if(!theInstance.isPresent())theInstance = Optional.of(load());
      return theInstance.get();
   }
   private static PriceCategoryBox load() throws PersistenceException {
      try{
         Integer typeKey = PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeKey("CinemaService", "PriceCategoryBox");
         Optional<ResultSet> rs = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter("CinemaService").selectEntriesOfTable("PriceCategory", typeKey);
         if(rs.isPresent()) rs.get().next();
         Integer id = rs.isPresent() ? rs.get().getInt("id") : IDManagerTransient.getTheInstance().getNextId();
         return PriceCategoryBox.instantiateRuntimeCopy(id);
      } catch (SQLException | NoConnectionException e) {
         throw new PersistenceException(e.getMessage());
      }
   }
   public PriceCategoryBox getTheObject(){
      return this;
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
