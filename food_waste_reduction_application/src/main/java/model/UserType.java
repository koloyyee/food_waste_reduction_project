package model;

/**
 * Types of User
 */
public enum UserType {
	Retailer(1), CharitableOrg(2), Consumer(3);
	
	private int code;

	private UserType (int code) {
		this.code = code;
	}
	
	public int code() {
		return code;
	}
}
