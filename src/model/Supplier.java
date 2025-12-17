package model;

import java.util.Objects;

/**
 * Represents a Supplier entity in the Car Spare Parts Management System.
 * 
 * @author cyita
 */
public class Supplier {
    
    private String supplierId;
    private String supplierNames;
    private String supplierContact;
    private String supplierEmail;
    private String address;

    /**
     * Default constructor.
     */
    public Supplier() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param supplierId the unique identifier for the supplier
     * @param supplierNames the name of the supplier
     * @param supplierContact the contact information of the supplier
     * @param supplierEmail the email address of the supplier
     * @param address the address of the supplier
     */
    public Supplier(String supplierId, String supplierNames, String supplierContact, 
            String supplierEmail, String address) {
        this.supplierId = supplierId;
        this.supplierNames = supplierNames;
        this.supplierContact = supplierContact;
        this.supplierEmail = supplierEmail;
        this.address = address;
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

    /**
     * Gets the supplier name.
     * 
     * @return the supplier name
     */
    public String getSupplierNames() {
        return supplierNames;
    }

    /**
     * Sets the supplier name.
     * 
     * @param supplierNames the supplier name to set
     */
    public void setSupplierNames(String supplierNames) {
        this.supplierNames = supplierNames;
    }

    /**
     * Gets the supplier contact information.
     * 
     * @return the supplier contact information
     */
    public String getSuppliercontact() {
        return supplierContact;
    }

    /**
     * Sets the supplier contact information.
     * 
     * @param supplierContact the supplier contact information to set
     */
    public void setSuppliercontact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    /**
     * Gets the supplier address.
     * 
     * @return the supplier address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the supplier address.
     * 
     * @param address the supplier address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the supplier email address.
     * 
     * @return the supplier email address
     */
    public String getSupplierEmail() {
        return supplierEmail;
    }

    /**
     * Sets the supplier email address.
     * 
     * @param supplierEmail the supplier email address to set
     */
    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
    
    @Override
    public String toString() {
        return "Supplier{"
                + "supplierId='" + supplierId + '\''
                + ", supplierNames='" + supplierNames + '\''
                + ", supplierContact='" + supplierContact + '\''
                + ", supplierEmail='" + supplierEmail + '\''
                + ", address='" + address + '\''
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
        Supplier supplier = (Supplier) obj;
        return Objects.equals(supplierId, supplier.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId);
    }
}
