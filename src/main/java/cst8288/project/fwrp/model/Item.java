package cst8288.project.fwrp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Items
 */
public class Item {
    private Long id;
    private String name;
    private String description;
    private LocalDate expiryDate;
    private BigDecimal price;
    private double discountRate = 0;
    private boolean isSurplus = false;
    private boolean isDonation = false;
    private int quantity;
    private boolean isAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    /**
     * discount rate start at 0% = 0.0, no discount. if 30% off, discount rate will
     * 1 - 0.3 = 0.7
     */
    public BigDecimal getPrice() {

        var bdDiscountRate = BigDecimal.valueOf(1 - discountRate);
        // round the price to 2 decimals
        return price.multiply(bdDiscountRate).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * If the expiry date is 1-week away from now, it should return is surplus.
     */
    public boolean checkSurplus() {
        return ChronoUnit.WEEKS.between(LocalDate.now(), this.expiryDate) <= 1;
    }

    public boolean isSurplus() {
        return isSurplus;
    }

    public void setSurplus(boolean isSurplus) {
        this.isSurplus = isSurplus;
    }

    public boolean isDonation() {
        return isDonation;
    }

    public void setDonation(boolean isDonation) {
        this.isDonation = isDonation;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", expiryDate=" + expiryDate +
            ", price=" + price +
            ", discountRate=" + discountRate +
            ", isSurplus=" + isSurplus +
            ", isDonation=" + isDonation +
            ", quantity=" + quantity +
            ", isAvailable=" + isAvailable +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
