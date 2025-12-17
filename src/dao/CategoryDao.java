package dao;

import java.util.List;
import model.Category;

/**
 * Data Access Object interface for Category entity operations.
 * Provides methods for CRUD operations on category data.
 * 
 * @author cyita
 */
public interface CategoryDao {
    
    /**
     * Creates a new category in the database.
     * 
     * @param categoryObj the category object to create
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int create(Category categoryObj);
    
    /**
     * Updates an existing category in the database.
     * 
     * @param categoryObj the category object with updated information
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int update(Category categoryObj);
    
    /**
     * Deletes a category from the database by its ID.
     * 
     * @param categoryId the ID of the category to delete
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int delete(String categoryId);
    
    /**
     * Searches for a category by its ID.
     * 
     * @param categoryId the ID of the category to search for
     * @return the Category object if found, null otherwise
     */
    Category searchById(String categoryId);
    
    /**
     * Retrieves all categories from the database.
     * 
     * @return a list of all Category objects
     */
    List<Category> displayAllCategories();
}
