package cst8288.project.fwrp.utils.exception;

/**
 * IllegalDiscountRate class represents an exception when discount rate is invalid.
 */
public class IllegalDiscountRate extends IllegalArgumentException {
		public IllegalDiscountRate(double discountRate) {
				super("Discount rate must be between 0.0 - 1.0: %.2f is incorrect".formatted(discountRate));
		}
}
