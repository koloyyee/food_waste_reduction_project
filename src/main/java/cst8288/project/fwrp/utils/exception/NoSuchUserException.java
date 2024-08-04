package cst8288.project.fwrp.utils.exception;


/**
 * NoSuchUserException class represents an exception when no such user is found.
 */
public class NoSuchUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoSuchUserException() {
		super("No such user");
	}

	public NoSuchUserException(String message ) {
		super(message);
	}
}
