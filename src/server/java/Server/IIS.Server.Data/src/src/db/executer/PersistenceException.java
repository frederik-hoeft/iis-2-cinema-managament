package src.db.executer;
/**
 * Defined as a runtime exception to make use of generated code more flexible and handy
 */
public class PersistenceException extends RuntimeException {
	public PersistenceException(String message) {
		super("Exception when trying to communicate with database: " + message);
	}
}
