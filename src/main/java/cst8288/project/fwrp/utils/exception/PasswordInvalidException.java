package cst8288.project.fwrp.utils.exception;

public class PasswordInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PasswordInvalidException() {
		super("Password is invalid");
	}
	public PasswordInvalidException(String message) {
		super(message);
	}

}
