package src.db.executer;
/**
 * Connection to the database. 
 * This object runs through 3 states and events (->): 
 * 1. No top connection data and no individual data present 
 * -> Establish connection with data d1 -> openDBConnection(d1)
 * 2. Connection data to mySQL independent of service -> topXY is present, individual connections.isEmpty() 
 * -> Create Database for a service if not already present, add triple (connectionData, java.sql.Connection, Sequencer) to individualConnections
 * 3. Connection d2 to service databases fully established, maybe extended by new individual connections 
 * 
 * Invariants:
 * 		0. for all i: topConnectionData.url = individualConnections.values().get(i).connectionData.url 
 * 		1. topConnectionData.isPresent <=> topPhysicalConnection.isPresent  
 * 		2. individualConnections.size()>0 => topConnectionData.isPresent
 * 
 * Most of the operations are protected, i.e. not visible from the generated code - all DB actions run through 
 * interfaces PersistenceD_LExecuter
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import src.db.connection.DBConnectionData;
import src.db.connection.NoConnectionException;
public class DBConnectionManager {
	private static final String errorNoDB = "No Database Connection Data provided!"; 
	private Optional<DBConnectionData> topConnectionData;   // Basic for accessing top root of database (service unknown)
	private Optional<Connection> topPhysicalConnection;     // Corresponding physical java.sql.Connection 
	
	private Map<String, ConnectionDataPhysicalSequencerTriple> individualConnections; // (ServiceName = DatabaseName) |-> (connectionData, physicalConnection), all entries have non null serviceName 
	private static DBConnectionManager instance = new DBConnectionManager();
	private DBConnectionManager() {
		this.topPhysicalConnection = Optional.empty();
		this.topConnectionData = Optional.empty();
		this.individualConnections = new HashMap<>();
	}
/**
 * Creates the topConnection (only for the root of all databases, if no database name is provided)
 * Puts (insert or update) an individual connection, if database name is provided in the collection of all connections
 *  
 * Initializes the sequencer, if database name is provided
 */
	protected void openDBConnection(DBConnectionData connectionData) throws SQLException, ClassNotFoundException, NoConnectionException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.setTopConnection(connectionData);
		if(connectionData.isDatabaseNamePresent()) {
// 			this.individualConnections.clear();    // NEWC - Deleted
			Connection physical = DriverManager.getConnection(connectionData.getUrl() + "/" + connectionData.getDatabaseName(), connectionData.getUser(), connectionData.getPassword());
			this.individualConnections.put(connectionData.getDatabaseName(), new ConnectionDataPhysicalSequencerTriple(connectionData, physical,this.initializeSequencing(physical, connectionData.getDatabaseName())));
		}
	}
// NEWC 	
/**	
 * Only used in local package by D_LExecuter's, public for test purposes.
 */
	public Connection getIndividualConnection(String serviceName) throws NoConnectionException{
		if(this.topPhysicalConnection.isEmpty()) throw new NoConnectionException(errorNoDB);
		if(!this.individualConnections.containsKey(serviceName)) throw new NoConnectionException(errorNoDB + ": " + serviceName); 
		return this.individualConnections.get(serviceName).physicalConnection;
	}	
	public Connection getRootConnection() throws NoConnectionException{
		if(this.topPhysicalConnection.isEmpty()) throw new NoConnectionException(errorNoDB);
		return this.topPhysicalConnection.get();
	}
	protected Integer getNextId(String serviceName) throws NoConnectionException, SQLException{
		if(this.topPhysicalConnection.isEmpty()) throw new NoConnectionException(errorNoDB);	 
		if(!this.individualConnections.containsKey(serviceName)) throw new NoConnectionException(errorNoDB + ": " + serviceName); 
		return this.individualConnections.get(serviceName).sequencer.getNextValue(); 
	}
// NEWCEnde	
/**	
 * Closes one individual DBConnection by 
 * 1) Closing it and 
 * 2) removing it from this.individualConnections
 */
	protected void close(String serviceName) throws SQLException {
		if(!this.individualConnections.containsKey(serviceName)) return; 	
		this.individualConnections.get(serviceName).physicalConnection.close();
		this.individualConnections.remove(serviceName);
	}
	protected void closeRootConnection() throws SQLException {
		if(this.topPhysicalConnection.isPresent()) this.topPhysicalConnection.get().close();
	}
/**
 * @return s Root Connection Data 
 */
	public Optional<DBConnectionData> getRootConnectionData() {
		return this.topConnectionData;
	}
/**
 * @return s individual connection Data for the given service name (= database name) 	
 */
	public Optional<DBConnectionData> getConnectionData(String serviceName) {
		if(this.individualConnections.containsKey(serviceName)) return Optional.of(this.individualConnections.get(serviceName).dbConnectionData);
		else 													return Optional.empty();
	}
// NEWCEnde	
/**
 * Checks existence of service table and single entry - if not creates it
 * Creates the sequencer representation afterwards
 */
	private ObjectSequencer initializeSequencing(Connection physical, String databaseName) throws NoConnectionException, SQLException {
		Statement s;
		try {
			s = physical.createStatement();
			s.executeUpdate(new DDLStatementTextCreator().createServiceTable(databaseName)); //Exception, if already existing with one entry with id = 1 and sequencerValue
			s.executeUpdate(new DMLStatementTextCreator().createTheServiceTableEntry(databaseName));
		} catch (SQLException e) {// ok
		}
		return new ObjectSequencer(databaseName, physical);
	}
/**	
 * For test purposes only
 */
	public Map<String, ConnectionDataPhysicalSequencerTriple> getIndividualConnections(){
		return this.individualConnections;
	}
	public void initialize() throws SQLException {
		for (ConnectionDataPhysicalSequencerTriple current : this.individualConnections.values()) current.physicalConnection.close();
		this.individualConnections.clear();
		if(this.topPhysicalConnection.isPresent()) this.topPhysicalConnection.get().close();
		this.topPhysicalConnection = Optional.empty();
		this.topConnectionData = Optional.empty();
	}
// ==========================================================
	public static DBConnectionManager getTheInstance() {return instance;}
	
// ====================== Private Part =======================
	private void setTopConnection(DBConnectionData connectionData) throws SQLException {
		DBConnectionData reducedData = new DBConnectionData(connectionData.getUrl(), connectionData.getUser(), connectionData.getPassword());
		this.topConnectionData = Optional.of(reducedData);
		this.topPhysicalConnection = Optional.of(DriverManager.getConnection(reducedData.getUrl() + "/", reducedData.getUser(), reducedData.getPassword()));
	}

/**
 * DAO for consistent triple of connection data (url, database, user, pwd), physical java.sql.Connection and index sequencer	
 */
	private class ConnectionDataPhysicalSequencerTriple{
		public final DBConnectionData dbConnectionData;
		public final Connection physicalConnection;
		public final ObjectSequencer sequencer;
		public ConnectionDataPhysicalSequencerTriple(DBConnectionData dbConnectionData, Connection physicalConnection, ObjectSequencer sequencer) {
			super();
			this.dbConnectionData = dbConnectionData;
			this.physicalConnection = physicalConnection;
			this.sequencer = sequencer;
		}
	}

}
