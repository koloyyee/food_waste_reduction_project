package cst8288.project.fwrp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * OrderedItem class represents as a historical item in the order.
 * used by Consumer and Charitable Organization.
 */
public record OrderedItem (Long id, String name, String description, BigDecimal price, int quantity, LocalDateTime createdAt) {
	public BigDecimal getPrice() {
        return  price.setScale(2, RoundingMode.HALF_EVEN);
	}
	public String getCreatedAtStr() {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return createdAt.format(formatter);
	}
}

