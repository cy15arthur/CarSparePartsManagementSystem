package dao;

import model.Category;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 * Unit tests for CategoryDao implementation.
 * Note: These tests require a test database to be set up.
 * 
 * @author cyita
 */
public class CategoryDaoTest {
    
    private CategoryDao categoryDao;
    private boolean databaseAvailable = false;
    
    @Before
    public void setUp() {
        categoryDao = new CategoryDaoImpl();
        
        // Check if database is available
        try {
            util.DatabaseConnectionManager.getConnection().close();
            databaseAvailable = true;
        } catch (SQLException e) {
            databaseAvailable = false;
            System.out.println("WARNING: Database not available. Some tests will be skipped.");
        }
    }
    
    @Test
    public void testCreateCategory() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            Category category = new Category("TEST001", "Test Category");
            int result = categoryDao.create(category);
            
            assertTrue("Category should be created successfully", result > 0);
            
            // Clean up
            categoryDao.delete("TEST001");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testCreateNullCategory() {
        int result = categoryDao.create(null);
        assertEquals("Creating null category should return 0", 0, result);
    }
    
    @Test
    public void testSearchById() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Create a category first
            Category category = new Category("TEST002", "Search Test Category");
            categoryDao.create(category);
            
            // Search for it
            Category found = categoryDao.searchById("TEST002");
            
            assertNotNull("Category should be found", found);
            assertEquals("Category ID should match", "TEST002", found.getCategoryId());
            assertEquals("Category name should match", "Search Test Category", found.getCategoryName());
            
            // Clean up
            categoryDao.delete("TEST002");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testSearchById_NotFound() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            Category found = categoryDao.searchById("NONEXISTENT");
            assertNull("Non-existent category should return null", found);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testSearchById_NullId() {
        Category found = categoryDao.searchById(null);
        assertNull("Null ID should return null", found);
    }
    
    @Test
    public void testUpdateCategory() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Create a category
            Category category = new Category("TEST003", "Original Name");
            categoryDao.create(category);
            
            // Update it
            category.setCategoryName("Updated Name");
            int result = categoryDao.update(category);
            
            assertTrue("Category should be updated successfully", result > 0);
            
            // Verify update
            Category updated = categoryDao.searchById("TEST003");
            assertNotNull(updated);
            assertEquals("Updated Name", updated.getCategoryName());
            
            // Clean up
            categoryDao.delete("TEST003");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testUpdateNullCategory() {
        int result = categoryDao.update(null);
        assertEquals("Updating null category should return 0", 0, result);
    }
    
    @Test
    public void testDeleteCategory() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Create a category first
            Category category = new Category("TEST004", "Delete Test Category");
            categoryDao.create(category);
            
            // Delete it
            int result = categoryDao.delete("TEST004");
            
            assertTrue("Category should be deleted successfully", result > 0);
            
            // Verify deletion
            Category deleted = categoryDao.searchById("TEST004");
            assertNull("Deleted category should not be found", deleted);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testDeleteCategory_NotFound() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            int result = categoryDao.delete("NONEXISTENT");
            assertEquals("Deleting non-existent category should return 0", 0, result);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testDeleteCategory_NullId() {
        int result = categoryDao.delete(null);
        assertEquals("Deleting with null ID should return 0", 0, result);
    }
    
    @Test
    public void testDisplayAllCategories() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            java.util.List<Category> categories = categoryDao.displayAllCategories();
            assertNotNull("Categories list should not be null", categories);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}
