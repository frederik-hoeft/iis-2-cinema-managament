package src.db.executer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import src.db.connection.NoConnectionException;
/**
 * ---> The returned optionals are present IFF a DB is in operation <-----
 */
public interface PersistenceDMLExecuter {
	Integer getNextId() throws PersistenceException;
	void insertInto(String tableName, String columnNames, String values) throws SQLException, NoConnectionException;
	void delete(String tableName, Integer id) throws SQLException, NoConnectionException;
	void deleteFromRelationTable(String tableName, Integer p1, Integer p2) throws SQLException, NoConnectionException;
	Optional<ResultSet> selectIdsOfEntriesOfTable(String tableName, Integer typeKey) throws SQLException, NoConnectionException;
	Optional<ResultSet> selectEntriesOfTable(String tableName, Integer typeKey) throws SQLException, NoConnectionException;
	Optional<ResultSet> selectAllEntriesOfRelationTable(String tableName) throws SQLException, NoConnectionException;
	Optional<ResultSet> selectAllEntriesOfTypeKeyTable() throws SQLException, NoConnectionException;
	Optional<ResultSet> selectIdSpecifiedCursorAleadyAtFirstRow(String tableName, Integer id)
			throws SQLException, NoConnectionException;
	void update(String tableName, String columnName, String value, Integer id)
			throws SQLException, NoConnectionException;
/**	
 * Retrieves the typename of the object with <id> stored in table <tableName> 
 * @throws PersistenceException 
 */
	String getNameOfConcreteType(Integer id, String tableName) throws PersistenceException;
}