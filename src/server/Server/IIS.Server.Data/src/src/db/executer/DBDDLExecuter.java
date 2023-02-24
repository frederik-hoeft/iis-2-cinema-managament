package src.db.executer;
import java.sql.SQLException;
import java.sql.Statement;

import src.db.connection.DBConnectionData;
import src.db.connection.NoConnectionException;
/**
 * Executes DDL Statements - Stateless!
 */
public class DBDDLExecuter implements PersistenceDDLExecuter {
	private DDLStatementTextCreator ddl;
	private DMLStatementTextCreator dml;
	private DBConnectionManager mgr;
	protected DBDDLExecuter() {
		super();
		this.mgr = DBConnectionManager.getTheInstance();
		this.ddl = new DDLStatementTextCreator();
		this.dml = new DMLStatementTextCreator();
	}
	@Override
	public void openDBConnection(DBConnectionData connectionData) throws ClassNotFoundException, SQLException, NoConnectionException {
		mgr.openDBConnection(connectionData);
	}
/**	
 * Extends the given connection to a concrete database with name <servicename>
 */
	@Override
	public void establishConnectionToDatabase(String servicename) throws ClassNotFoundException, SQLException, NoConnectionException {
		mgr.openDBConnection(this.extendWithServiceName(mgr.getRootConnectionData().get(), servicename));
	}
// NEWC - Delete Start
//	@Override
//	public void closeConnection() throws SQLException {
//		this.mgr.close();
//	}
// NEWC - Delete End
// NEWC - Insert Start	
	@Override
	public void closeConnection(String serviceName) throws SQLException {
		this.mgr.close(serviceName);
	}
	@Override
	public void closeRootConnection() throws SQLException {
		this.mgr.closeRootConnection();
	}
// NEWC - Insert End
	@Override
	public void createDatabase(String servicename) throws SQLException, NoConnectionException {
		rootStmt().executeUpdate(ddl.createDatabase(servicename));
	}
	@Override
	public void createClassTable(String servicename, String classname, String attributeList) throws SQLException, NoConnectionException {
		stmt(servicename).executeUpdate(ddl.createClassTable(servicename, classname, attributeList));
	}
	@Override
	public void createRelationTable(String serviceName, String associationName, String p1Type, String p2Type) throws SQLException, NoConnectionException {
		stmt(serviceName).executeUpdate(ddl.createRelationTable(associationName, p1Type, p2Type));
	}
	@Override
	public void createTypeKeyTable(String servicename) throws SQLException, NoConnectionException {
		stmt(servicename).executeUpdate(ddl.createTypeKeyTable(servicename));
	}
	@Override
	public void createTypeKeyTableEntry(String servicename, String relName) throws SQLException, NoConnectionException {
		stmt(servicename).executeUpdate(dml.createTypeKeyTableEntry(servicename, mgr.getNextId(servicename), relName));
	}
	@Override
	public boolean typeKeyTableExists(String servicename) throws SQLException, NoConnectionException {
		return stmt(servicename).executeQuery(dml.existsTypeKeysTableIn(servicename)).next();
	}
	public boolean tableExists(String serviceName, String tableName) throws SQLException, NoConnectionException {
		return stmt(serviceName).executeQuery(dml.existsTable(tableName)).next();
	}
	public void dropDatabase(DBConnectionData connectionData) throws PersistenceException{
		DBConnectionData connectionDataToRoot = new DBConnectionData(connectionData.getUrl(), connectionData.getUser(), connectionData.getPassword());
		String databaseName = connectionData.getDatabaseName();
		try {
			this.openDBConnection(connectionDataToRoot); // Opens DB Connection to root of all databases
			if(databaseExists(databaseName)) rootStmt().executeUpdate(ddl.dropDatabase(databaseName));
			mgr.closeRootConnection();
		}catch(NoConnectionException | SQLException | ClassNotFoundException nce) {
			throw new PersistenceException(nce.getMessage() + ". No connection possible to database " + databaseName + ". URL = " + connectionDataToRoot.getUrl() + ". User = " + connectionDataToRoot.getUser());
		}
	}

// ====================== Auxiliaries ===============================	
	private DBConnectionData extendWithServiceName(DBConnectionData current, String serviceName) {
		return new DBConnectionData(current.getUrl(), serviceName, current.getUser(), current.getPassword());
	}
// NEWC - Delete Start 
//	private Statement stmt() throws SQLException, NoConnectionException {
//		return mgr.getIndividualConnection().createStatement();
//	}
// NEWC - Delete End
// NEWC - Insert Start	
	private Statement stmt(String serviceName) throws SQLException, NoConnectionException {
		return mgr.getIndividualConnection(serviceName).createStatement();
	}
	private Statement rootStmt() throws SQLException, NoConnectionException {
		return mgr.getRootConnection().createStatement();
	}
// NEWC - Insert End
	private boolean databaseExists(String databaseName) throws SQLException, NoConnectionException {
		return rootStmt().executeQuery(ddl.dataBaseExists(databaseName)).next();
	}
}
