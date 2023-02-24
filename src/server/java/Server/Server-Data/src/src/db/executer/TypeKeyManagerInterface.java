package src.db.executer;

public interface TypeKeyManagerInterface {

	void initializeFor(String serviceName);

	String getTypeName(String serviceName, Integer key);

	Integer getTypeKey(String serviceName, String typeName) throws PersistenceException;

}