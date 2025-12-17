package dao;

import java.util.List;
import model.Sales;

/**
 * Data Access Object interface for Sales entity operations.
 * Provides methods for CRUD operations on sales data.
 * 
 * @author cyita
 */
public interface SalesDao {
    
    /**
     * Creates a new sales record in the database.
     * 
     * @param salesObj the sales object to create
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int create(Sales salesObj);
    
    /**
     * Searches for a sales record by its ID.
     * 
     * @param salesId the ID of the sales record to search for
     * @return the Sales object if found, null otherwise
     */
    Sales searchById(String salesId);
    
    /**
     * Retrieves all sales records from the database.
     * 
     * @return a list of all Sales objects
     */
    List<Sales> displayAllSales();
}
