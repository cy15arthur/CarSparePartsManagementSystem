package model;

import java.util.Objects;

/**
 * Represents a Customer entity in the Car Spare Parts Management System.
 * 
 * @author cyita
 */
public class Customer {
    
    private String customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;

    /**
     * Default constructor.
     */
    public Customer() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param customerId the unique identifier for the customer
     * @param customerFirstName the first name of the customer
     * @param customerLastName the last name of the customer
     * @param customerEmail the email address of the customer
     * @param customerPhone the phone number of the customer
     */
    public Customer(String customerId, String customerFirstName, String customerLastName, 
            String customerEmail, String customerPhone) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
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
     * Gets the customer's first name.
     * 
     * @return the customer's first name
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * Sets the customer's first name.
     * 
     * @param customerFirstName the first name to set
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     * Gets the customer's last name.
     * 
     * @return the customer's last name
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     * Sets the customer's last name.
     * 
     * @param customerLastName the last name to set
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    /**
     * Gets the customer's email address.
     * 
     * @return the customer's email address
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the customer's email address.
     * 
     * @param customerEmail the email address to set
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Gets the customer's phone number.
     * 
     * @return the customer's phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the customer's phone number.
     * 
     * @param customerPhone the phone number to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    @Override
    public String toString() {
        return "Customer{"
                + "customerId='" + customerId + '\''
                + ", customerFirstName='" + customerFirstName + '\''
                + ", customerLastName='" + customerLastName + '\''
                + ", customerEmail='" + customerEmail + '\''
                + ", customerPhone='" + customerPhone + '\''
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
        Customer customer = (Customer) obj;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
