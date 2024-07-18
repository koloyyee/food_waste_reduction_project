package cst8288.project.fwrp.model;

import java.util.Arrays;

/**
 * Types of User
 */
public enum UserType {
	Retailer(1), CharitableOrg(2), Consumer(3);

	final int code;

	UserType(int code) {
		this.code = code;
	}
}
