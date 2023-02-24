package src.db.executer;

public class DBExecuterFactoryNoDB extends PersistenceExecuterFactory {
/** 
 * 	Can only be used from PersistenceExecuterFactory.getConfiguredFactory()
 */
	protected DBExecuterFactoryNoDB() {}
	public PersistenceDDLExecuter getDBDDLExecuter() {
		return new DBDDLExecuterNoBDB();
	}
	public PersistenceDMLExecuter getDBDMLExecuter(String serviceName) {
		return new DBDMLExecuterNoDB();
	}
	public TypeKeyManagerInterface getTypeKeyManager() {
		return TypeKeyManagerNoDB.getTheInstance();
	}
	public String getModeText() {
		return "Mode = No Database";
	}
	public boolean isDBPresent() {
		return false;
	}
}
