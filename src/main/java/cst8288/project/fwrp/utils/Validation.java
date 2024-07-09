package cst8288.project.fwrp.utils;

import java.util.regex.Pattern;

/**
 * Input validation <br>
 * 
 * Password:  <br>
 * must contain at least one digit 0-9.   <br>
 * must contain at least one lowercase A-Z and a-z .    <br>
 * must contain at least one symbols <br>
 * must contain a length of at least 8 characters and a maximum of 99 characters.   <br>
 */
public class Validation {
	
	private final static Logger logger = LoggerFactory.getLogger();

	private final static String PW_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,99}$";
	private final static Pattern pattern = Pattern.compile(PW_PATTERN);


	public boolean checkPhone(String phone) {
		// (xxx)xxx-xxxx
		
		return false;
	}

	/**
	 * Check if the password is 8-99 in length. Include upper case, lower case,
	 * symbols, number
	 */
	public boolean checkPassword(String password) {
		logger.warn("Password invalid");
		return pattern.matcher(password).find();
	}
}
