package src.db.executer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import src.db.connection.NoConnectionException;
import src.idManagement.IDManagerTransient;
/**
 * The substitute for the DML Executer, if no DB is in operation --> Most methods do nothing
 */
public class DBDMLExecuterNoDB implements PersistenceDMLExecuter {
	protected DBDMLExecuterNoDB() {}
	public Integer getNextId() {
		return IDManagerTransient.getTheInstance().getNextId();
	}
	public void insertInto(String tableName, String columnNames, String values){}
	public void delete(String tableName, Integer id) throws SQLException, NoConnectionException{}
	public void deleteFromRelationTable(String tableName, Integer p1, Integer p2){}
	public Optional<ResultSet> selectIdsOfEntriesOfTable(String tableName, Integer typeKey){return no();}
	public Optional<ResultSet> selectEntriesOfTable(String tableName, Integer typeKey){return no();}
	public Optional<ResultSet> selectAllEntriesOfRelationTable(String tableName){return no();}
	public Optional<ResultSet> selectAllEntriesOfTypeKeyTable(){return no();}
	public Optional<ResultSet> selectIdSpecifiedCursorAleadyAtFirstRow(String tableName, Integer id){return no();}
	public void update(String tableName, String columnName, String value, Integer id){}
	public String getNameOfConcreteType(Integer id, String tableName){
		return "";
	}
	private Optional<ResultSet> no(){
		return Optional.empty();
	}
}
