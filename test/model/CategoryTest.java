package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Category model class.
 * Tests constructors, getters, setters, equals, hashCode, and toString methods.
 * 
 * @author cyita
 */
public class CategoryTest {
    
    @Test
    public void testDefaultConstructor() {
        Category category = new Category();
        assertNotNull("Category object should be created", category);
        assertNull("Category ID should be null initially", category.getCategoryId());
        assertNull("Category name should be null initially", category.getCategoryName());
    }
    
    @Test
    public void testParameterizedConstructor() {
        String categoryId = "CAT001";
        String categoryName = "Engine Parts";
        
        Category category = new Category(categoryId, categoryName);
        
        assertNotNull("Category object should be created", category);
        assertEquals("Category ID should match", categoryId, category.getCategoryId());
        assertEquals("Category name should match", categoryName, category.getCategoryName());
    }
    
    @Test
    public void testGetCategoryId() {
        Category category = new Category();
        category.setCategoryId("CAT001");
        assertEquals("CAT001", category.getCategoryId());
    }
    
    @Test
    public void testSetCategoryId() {
        Category category = new Category();
        category.setCategoryId("CAT002");
        assertEquals("CAT002", category.getCategoryId());
    }
    
    @Test
    public void testGetCategoryName() {
        Category category = new Category();
        category.setCategoryName("Brake Parts");
        assertEquals("Brake Parts", category.getCategoryName());
    }
    
    @Test
    public void testSetCategoryName() {
        Category category = new Category();
        category.setCategoryName("Transmission Parts");
        assertEquals("Transmission Parts", category.getCategoryName());
    }
    
    @Test
    public void testEquals_SameObject() {
        Category category = new Category("CAT001", "Engine Parts");
        assertTrue("Category should equal itself", category.equals(category));
    }
    
    @Test
    public void testEquals_EqualObjects() {
        Category category1 = new Category("CAT001", "Engine Parts");
        Category category2 = new Category("CAT001", "Engine Parts");
        assertTrue("Categories with same ID should be equal", category1.equals(category2));
    }
    
    @Test
    public void testEquals_DifferentObjects() {
        Category category1 = new Category("CAT001", "Engine Parts");
        Category category2 = new Category("CAT002", "Brake Parts");
        assertFalse("Categories with different IDs should not be equal", category1.equals(category2));
    }
    
    @Test
    public void testEquals_NullObject() {
        Category category = new Category("CAT001", "Engine Parts");
        assertFalse("Category should not equal null", category.equals(null));
    }
    
    @Test
    public void testEquals_DifferentClass() {
        Category category = new Category("CAT001", "Engine Parts");
        String notCategory = "Not a Category";
        assertFalse("Category should not equal different class", category.equals(notCategory));
    }
    
    @Test
    public void testHashCode_EqualObjects() {
        Category category1 = new Category("CAT001", "Engine Parts");
        Category category2 = new Category("CAT001", "Brake Parts"); // Different name, same ID
        assertEquals("Equal categories should have same hash code", 
                category1.hashCode(), category2.hashCode());
    }
    
    @Test
    public void testHashCode_Consistency() {
        Category category = new Category("CAT001", "Engine Parts");
        int hashCode1 = category.hashCode();
        int hashCode2 = category.hashCode();
        assertEquals("Hash code should be consistent", hashCode1, hashCode2);
    }
    
    @Test
    public void testToString() {
        Category category = new Category("CAT001", "Engine Parts");
        String toString = category.toString();
        
        assertNotNull("toString should not be null", toString);
        assertTrue("toString should contain category ID", toString.contains("CAT001"));
        assertTrue("toString should contain category name", toString.contains("Engine Parts"));
    }
    
    @Test
    public void testToString_WithNullValues() {
        Category category = new Category();
        String toString = category.toString();
        
        assertNotNull("toString should not be null even with null values", toString);
    }
}

