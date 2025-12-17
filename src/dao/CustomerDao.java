package dao;

import java.util.List;
import model.Customer;

/**
 * Data Access Object interface for Customer entity operations.
 * Provides methods for CRUD operations on customer data.
 * 
 * @author cyita
 */
public interface CustomerDao {
    
    /**
     * Creates a new customer in the database.
     * 
     * @param customerObj the customer object to create
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int create(Customer customerObj);
    
    /**
     * Updates an existing customer in the database.
     * 
     * @param customerObj the customer object with updated information
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int update(Customer customerObj);
    
    /**
     * Deletes a customer from the database by its ID.
     * 
     * @param customerId the ID of the customer to delete
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int delete(String customerId);
    
    /**
     * Searches for a customer by its ID.
     * 
     * @param customerId the ID of the customer to search for
     * @return the Customer object if found, null otherwise
     */
    Customer searchById(String customerId);
    
    /**
     * Retrieves all customers from the database.
     * 
     * @return a list of all Customer objects
     */
    List<Customer> displayAllCustomers();
}
