module src {
	requires java.sql;
	
	exports src.db;
	exports src.db.connection;
	exports src.db.executer;
	exports src.idManagement;
}