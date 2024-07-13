package cst8288.project.fwrp.utils.exception;

public class NoSuchUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoSuchUserException() {
		super("No such user");
	}

	public NoSuchUserException(String message ) {
		super(message);
	}
}
