package cst8288.project.fwrp.model;

/**
 * Transaction Type
 */
public enum TransactionType {
	Purchased(1), Donated(2);

	final int code;

	TransactionType(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}
}
