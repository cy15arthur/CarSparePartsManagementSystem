package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Sales entity in the Car Spare Parts Management System.
 * 
 * @author cyita
 */
public class Sales {
    
    private String salesId;
    private String partCode;
    private String customerId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime saleDate;

    /**
     * Default constructor.
     */
    public Sales() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param salesId the unique identifier for the sales record
     * @param partCode the code of the part sold
     * @param customerId the ID of the customer who made the purchase
     * @param quantity the quantity of parts sold
     * @param totalPrice the total price of the sale
     * @param saleDate the date and time of the sale
     */
    public Sales(String salesId, String partCode, String customerId, int quantity, 
            double totalPrice, LocalDateTime saleDate) {
        this.salesId = salesId;
        this.partCode = partCode;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    /**
     * Gets the sales ID.
     * 
     * @return the sales ID
     */
    public String getSalesId() {
        return salesId;
    }

    /**
     * Sets the sales ID.
     * 
     * @param salesId the sales ID to set
     */
    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    /**
     * Gets the part code.
     * 
     * @return the part code
     */
    public String getPartCode() {
        return partCode;
    }

    /**
     * Sets the part code.
     * 
     * @param partCode the part code to set
     */
    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    /**
     * Gets the customer ID.
     * 
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     * 
     * @param customerId the customer ID to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the quantity.
     * 
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * 
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total price.
     * 
     * @return the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price.
     * 
     * @param totalPrice the total price to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the sale date.
     * 
     * @return the sale date
     */
    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the sale date.
     * 
     * @param saleDate the sale date to set
     */
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
    
    @Override
    public String toString() {
        return "Sales{"
                + "salesId='" + salesId + '\''
                + ", partCode='" + partCode + '\''
                + ", customerId='" + customerId + '\''
                + ", quantity=" + quantity
                + ", totalPrice=" + totalPrice
                + ", saleDate=" + saleDate
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sales sales = (Sales) obj;
        return Objects.equals(salesId, sales.salesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesId);
    }
}
