package model;

import java.util.Objects;

/**
 * Represents a Part entity in the Car Spare Parts Management System.
 * 
 * @author cyita
 */
public class Part {
    
    private String partCode;
    private String partName;
    private String brand;
    private String categoryId;
    private double price;
    private double stockQuantity;
    private String supplierId;

    /**
     * Default constructor.
     */
    public Part() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param partCode the unique code for the part
     * @param partName the name of the part
     * @param brand the brand of the part
     * @param categoryId the ID of the category this part belongs to
     * @param price the price of the part
     * @param stockQuantity the quantity of the part in stock
     * @param supplierId the ID of the supplier for this part
     */
    public Part(String partCode, String partName, String brand, String categoryId, 
            double price, double stockQuantity, String supplierId) {
        this.partCode = partCode;
        this.partName = partName;
        this.brand = brand;
        this.categoryId = categoryId;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.supplierId = supplierId;
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
     * Gets the part name.
     * 
     * @return the part name
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets the part name.
     * 
     * @param partName the part name to set
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * Gets the brand.
     * 
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand.
     * 
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the category ID.
     * 
     * @return the category ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID.
     * 
     * @param categoryId the category ID to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the price.
     * 
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     * 
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the stock quantity.
     * 
     * @return the stock quantity
     */
    public double getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity.
     * 
     * @param stockQuantity the stock quantity to set
     */
    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Gets the supplier ID.
     * 
     * @return the supplier ID
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the supplier ID.
     * 
     * @param supplierId the supplier ID to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    @Override
    public String toString() {
        return "Part{"
                + "partCode='" + partCode + '\''
                + ", partName='" + partName + '\''
                + ", brand='" + brand + '\''
                + ", categoryId='" + categoryId + '\''
                + ", price=" + price
                + ", stockQuantity=" + stockQuantity
                + ", supplierId='" + supplierId + '\''
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
        Part part = (Part) obj;
        return Objects.equals(partCode, part.partCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partCode);
    }
}
