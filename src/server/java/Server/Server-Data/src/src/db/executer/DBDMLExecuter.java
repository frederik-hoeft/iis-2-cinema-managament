package src.db.executer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import src.db.connection.NoConnectionException;
/**
 * Execution of DML Commands for a given database schema
 * Stateless objects
 */
public class DBDMLExecuter implements PersistenceDMLExecuter {
	private final DMLStatementTextCreator dml;
	private DBConnectionManager mgr;
	private final String databaseSchema; // NEWC - Inserted
	protected DBDMLExecuter(String serviceName) {
		super();
		this.mgr = DBConnectionManager.getTheInstance();
		this.dml = new DMLStatementTextCreator();
		this.databaseSchema = serviceName; // NEWC - Inserted
	}
	public String getDataBaseSchema() {
		return this.databaseSchema;
	}
	public Integer getNextId() throws PersistenceException {
// NEWC Deleted try {return mgr.getNextId();}catch(NoConnectionException | SQLException e) {throw new PersistenceException(e.getMessage());}
		try {return mgr.getNextId(this.databaseSchema);}catch(NoConnectionException | SQLException e) {throw new PersistenceException(e.getMessage());} // NEWC Inserted
	}
	public void insertInto(String tableName, String columnNames, String values) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.insertInto(tableName, columnNames, values));
	}
	public void delete(String tableName, Integer id) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.delete(tableName, id));
	}
	public void deleteFromRelationTable(String tableName, Integer p1, Integer p2) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.deleteFromRelationTable(tableName, p1, p2));
	}
	public Optional<ResultSet> selectIdsOfEntriesOfTable(String tableName, Integer typeKey) throws SQLException, NoConnectionException {
		return Optional.of(stmt().executeQuery(this.dml.selectIdsOfEntriesOfTable(tableName, typeKey)));
	}
	public Optional<ResultSet> selectEntriesOfTable(String tableName, Integer typeKey) throws SQLException, NoConnectionException {
		return Optional.of(stmt().executeQuery(this.dml.selectEntriesOfTable(tableName, typeKey)));
	}
	public Optional<ResultSet> selectAllEntriesOfRelationTable(String tableName) throws SQLException, NoConnectionException {
		return Optional.of(stmt().executeQuery(this.dml.selectAllEntriesOfRelationTable(tableName)));
	}
// NEWC - Deleted	
//	public Optional<ResultSet> selectAllEntriesOfTypeKeyTable(String serviceName) throws SQLException, NoConnectionException {
//		return Optional.of(stmt().executeQuery(this.dml.selectAllEntriesOfTypeKeyTable(serviceName)));
//	}
// NEWC - Deleted End
// NEWC - Inserted	
	public Optional<ResultSet> selectAllEntriesOfTypeKeyTable() throws SQLException, NoConnectionException {
		return Optional.of(stmt().executeQuery(this.dml.selectAllEntriesOfTypeKeyTable(this.databaseSchema)));
	}
// NEWC - Inserted End	
	public Optional<ResultSet> selectIdSpecifiedCursorAleadyAtFirstRow(String tableName, Integer id) throws SQLException, NoConnectionException {
		ResultSet rs = stmt().executeQuery(this.dml.selectIdSpecifiedCursorAleadyAtFirstRow(tableName, id));
		boolean hasRow = rs.next();
		if(!hasRow) throw new SQLException("Object with id = " + id + " not found in table " + tableName);
		return Optional.of(rs);
	}
	public void update(String tableName, String columnName, String value, Integer id) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.update(tableName, columnName, value, id));
	}
/**	
 * Retrieves the typename of the object with <id> stored in table <tableName> 
 * @throws PersistenceException 
 */
//NEWC - Delete	public String getNameOfConcreteType(Integer id, String tableName, String serviceName) throws PersistenceException {
	public String getNameOfConcreteType(Integer id, String tableName) throws PersistenceException {		// NEWC - Insert
		ResultSet rs1,rs2 = null;
		try {
			rs1 = stmt().executeQuery("SELECT typeKey FROM " + tableName + " WHERE id = " + id);
			if(!rs1.next()) throw new PersistenceException("Retrieving typekey for id " + id + ", but found no entry in table " + tableName);
			Integer typeKey = rs1.getInt("typeKey"); // Type Key is in column 2
// NEWC - Delete	return PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeName(serviceName, typeKey);			
			return PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().getTypeName(this.databaseSchema, typeKey); // NEWC - Insert
		} catch (SQLException | NoConnectionException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	private Statement stmt() throws SQLException, NoConnectionException {
// NEWC-Delete	return mgr.getIndividualConnection().createStatement();  
		return mgr.getIndividualConnection(this.databaseSchema).createStatement();  // NEWC-Insert
	}
}
