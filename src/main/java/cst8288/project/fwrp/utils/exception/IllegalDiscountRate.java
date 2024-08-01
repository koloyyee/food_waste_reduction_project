package cst8288.project.fwrp.utils.exception;

public class IllegalDiscountRate extends IllegalArgumentException {
		public IllegalDiscountRate(double discountRate) {
				super("Discount rate must be between 0.0 - 1.0: %.2f is incorrect".formatted(discountRate));
		}
}
