package dao;

import java.util.List;
import model.Part;

/**
 * Data Access Object interface for Part entity operations.
 * Provides methods for CRUD operations on part data.
 * 
 * @author cyita
 */
public interface PartDao {
    
    /**
     * Creates a new part in the database.
     * 
     * @param partObj the part object to create
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int create(Part partObj);
    
    /**
     * Updates an existing part in the database.
     * 
     * @param partObj the part object with updated information
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int update(Part partObj);
    
    /**
     * Deletes a part from the database by its code.
     * 
     * @param partCode the code of the part to delete
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int delete(String partCode);
    
    /**
     * Searches for a part by its code.
     * 
     * @param partCode the code of the part to search for
     * @return the Part object if found, null otherwise
     */
    Part searchByCode(String partCode);
    
    /**
     * Retrieves all parts from the database.
     * 
     * @return a list of all Part objects
     */
    List<Part> displayAllParts();
    
    /**
     * Checks if a part with the given code exists in the database.
     * 
     * @param partCode the code of the part to check
     * @return true if the part exists, false otherwise
     */
    boolean exists(String partCode);
}
