package src.db.executer;

import java.sql.SQLException;

import src.db.connection.DBConnectionData;
import src.db.connection.NoConnectionException;
/**
 * In "No DB" mode these operations do nothing
 */
public class DBDDLExecuterNoBDB implements PersistenceDDLExecuter {
	protected DBDDLExecuterNoBDB() {
		
	}
	
	public void openDBConnection(DBConnectionData connectionData){}
	public void establishConnectionToDatabase(String servicename){}
	public void closeConnection(String serviceName) throws SQLException {}
	public void closeRootConnection() throws SQLException {}
	public void createDatabase(String servicename){}
	public void createClassTable(String servicename, String classname, String attributeList){}
	public void createRelationTable(String serviceName, String associationName, String p1Type, String p2Type){}
	public void createTypeKeyTable(String servicename){}
	public void createTypeKeyTableEntry(String servicename, String relName){}
	public boolean typeKeyTableExists(String servicename) throws SQLException, NoConnectionException {return false;}
	public boolean tableExists(String serviceName, String tableName) throws SQLException, NoConnectionException {return false;}
	public void dropDatabase(DBConnectionData connectionData) {}
}
