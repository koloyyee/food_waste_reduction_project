package cst8288.project.fwrp.utils.exception;

/**
 * PasswordInvalidException class represents an exception when password is invalid.
 */
public class PasswordInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PasswordInvalidException() {
		super("Password is invalid");
	}
	public PasswordInvalidException(String message) {
		super(message);
	}

}
