package dao;

import java.util.List;
import model.Supplier;

/**
 * Data Access Object interface for Supplier entity operations.
 * Provides methods for CRUD operations on supplier data.
 * 
 * @author cyita
 */
public interface SupplierDao {
    
    /**
     * Creates a new supplier in the database.
     * 
     * @param supplierObj the supplier object to create
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int create(Supplier supplierObj);
    
    /**
     * Updates an existing supplier in the database.
     * 
     * @param supplierObj the supplier object with updated information
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int update(Supplier supplierObj);
    
    /**
     * Deletes a supplier from the database by its ID.
     * 
     * @param supplierId the ID of the supplier to delete
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int delete(String supplierId);
    
    /**
     * Searches for a supplier by its ID.
     * 
     * @param supplierId the ID of the supplier to search for
     * @return the Supplier object if found, null otherwise
     */
    Supplier searchByCode(String supplierId);
    
    /**
     * Retrieves all suppliers from the database.
     * 
     * @return a list of all Supplier objects
     */
    List<Supplier> displayAllSuppliers();
}
