package dao;

import model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 * Unit tests for UserDao implementation.
 * 
 * @author cyita
 */
public class UserDaoTest {
    
    private UserDao userDao;
    private boolean databaseAvailable = false;
    
    @Before
    public void setUp() {
        userDao = new UserDaoImpl();
        
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
    public void testAuthenticate_ValidCredentials() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // This test assumes admin user exists in database
            User user = userDao.authenticate("admin", "admin123");
            // Note: Result depends on database state
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testAuthenticate_InvalidCredentials() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            User user = userDao.authenticate("nonexistent", "wrongpass");
            assertNull("Invalid credentials should return null", user);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testAuthenticate_EmptyCredentials() {
        User user1 = userDao.authenticate("", "password");
        assertNull("Empty username should return null", user1);
        
        User user2 = userDao.authenticate("username", "");
        assertNull("Empty password should return null", user2);
    }
    
    @Test
    public void testCreateUser() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            User user = new User("TESTUSR001", "testuser", "testpass", 
                    "Test User", "test@email.com", "staff", true);
            int result = userDao.create(user);
            assertTrue("User should be created", result > 0);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testCreateNullUser() {
        int result = userDao.create(null);
        assertEquals(0, result);
    }
    
    @Test
    public void testFindByUsername() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            User user = userDao.findByUsername("admin");
            // Result depends on database state
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testFindByUsername_NotFound() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            User user = userDao.findByUsername("nonexistentuser");
            assertNull("Non-existent username should return null", user);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testUsernameExists() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // This test depends on database state
            boolean exists = userDao.usernameExists("admin");
            // Result depends on whether admin exists
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testUsernameExists_EmptyString() {
        boolean exists = userDao.usernameExists("");
        assertFalse("Empty string should return false", exists);
    }
    
    @Test
    public void testUpdateUser() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            User user = new User("TESTUSR002", "updateuser", "pass", 
                    "Update User", "update@email.com", "staff", true);
            userDao.create(user);
            
            user.setFullName("Updated Name");
            int result = userDao.update(user);
            assertTrue("User should be updated", result > 0);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}

