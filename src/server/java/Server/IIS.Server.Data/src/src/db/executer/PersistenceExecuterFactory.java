package src.db.executer;
/**
 * Abstract factory.
 * Implementations decide in which mode we work: With or without database 
 */
public abstract class PersistenceExecuterFactory {
// The default behavior of the generator is to generate code assuming no DB available 	
	private static PersistenceExecuterFactory configuredFactory = new DBExecuterFactoryNoDB();
	public static final PersistenceExecuterFactory getConfiguredFactory() {
		return configuredFactory;
	}
/**	
 * Sets generation control to explicit database handling 
 */
	public static void setUseDataBase() {
		configuredFactory = new DBExecuterFactory();
	}
/**	
 * Sets generation control to no DB Mode handling 
 */
	public static void setUseNoDataBase() {
		configuredFactory = new DBExecuterFactoryNoDB();
	}
	public abstract PersistenceDDLExecuter getDBDDLExecuter();
	public abstract PersistenceDMLExecuter getDBDMLExecuter(String serviceName);
	public abstract TypeKeyManagerInterface getTypeKeyManager();
	public abstract String getModeText();
	public abstract boolean isDBPresent();
}