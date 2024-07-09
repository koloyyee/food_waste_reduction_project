package cst8288.project.fwrp.model;

import java.util.Arrays;

/**
 * Types of User
 */
public enum UserType {
	Retailer(1), CharitableOrg(2), Consumer(3);

	private int code;

	private UserType(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

	public static UserType getByCode(int code) {
		return Arrays.stream(UserType.values()).filter(t -> t.code() == code).findFirst().get();
	}
}
