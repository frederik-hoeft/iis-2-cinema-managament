package exceptions;

/**
 * Exception thrown, if a restriction in a bidirectional association (e.g. containment) is violated
 */
public class ConstraintViolation extends Exception {
	private static final long serialVersionUID = 1L;
	public ConstraintViolation(String message) {
		super(message);
	}
}
