package src.db.executer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.db.connection.NoConnectionException;
/**
 * Represents the current value of the objectSequencer for a service
 * Invariant: serviceName = (databaseName of dbConnection)
 */
class ObjectSequencer {
	private Integer currentValue;
	private Connection dbConnection; 
	private String serviceName; 
	public ObjectSequencer(String serviceName, Connection dbConnection) throws NoConnectionException, SQLException {
		this.dbConnection = dbConnection;
		this.serviceName = serviceName;
		this.currentValue = this.loadCurrentValue();
	}
	private Integer loadCurrentValue() throws SQLException {
		ResultSet result = this.dbConnection.createStatement().executeQuery("SELECT objectSequencer FROM " + serviceName + " WHERE id = 1");
		result.next();
		return result.getInt(1);
	}
	Integer getNextValue() throws SQLException {
		currentValue++;
		this.dbConnection.createStatement().executeUpdate("UPDATE " + serviceName + " SET objectSequencer = " + currentValue + " WHERE id = 1");
		return this.currentValue;
	}
}
