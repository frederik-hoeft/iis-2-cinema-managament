package src.db.executer;
/**
 * A dummy class for the simulation of a type key manager, if no database is available 
 */
public class TypeKeyManagerNoDB implements TypeKeyManagerInterface {
	private static TypeKeyManagerNoDB theInstance = new TypeKeyManagerNoDB();
	private TypeKeyManagerNoDB() {}
	public static TypeKeyManagerNoDB getTheInstance() {return theInstance;}
	
	public void initializeFor(String serviceName) {
		return;
	}
	public String getTypeName(String serviceName, Integer key) {
		return "";
	}
	public Integer getTypeKey(String serviceName, String typeName) throws PersistenceException {
		return 0;
	}
}
