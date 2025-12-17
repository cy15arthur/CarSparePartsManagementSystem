package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for User model class.
 * 
 * @author cyita
 */
public class UserTest {
    
    @Test
    public void testDefaultConstructor() {
        User user = new User();
        assertNotNull(user);
        assertNull(user.getUserId());
        assertFalse(user.isActive());
    }
    
    @Test
    public void testParameterizedConstructor() {
        User user = new User("USR001", "admin", "password123", "Admin User", 
                "admin@email.com", "admin", true);
        
        assertEquals("USR001", user.getUserId());
        assertEquals("admin", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("Admin User", user.getFullName());
        assertEquals("admin@email.com", user.getEmail());
        assertEquals("admin", user.getRole());
        assertTrue(user.isActive());
    }
    
    @Test
    public void testGettersAndSetters() {
        User user = new User();
        
        user.setUserId("USR002");
        user.setUsername("staff");
        user.setPassword("pass123");
        user.setFullName("Staff User");
        user.setEmail("staff@email.com");
        user.setRole("staff");
        user.setActive(true);
        
        assertEquals("USR002", user.getUserId());
        assertEquals("staff", user.getUsername());
        assertEquals("pass123", user.getPassword());
        assertEquals("Staff User", user.getFullName());
        assertEquals("staff@email.com", user.getEmail());
        assertEquals("staff", user.getRole());
        assertTrue(user.isActive());
    }
    
    @Test
    public void testIsAdmin() {
        User adminUser = new User("USR001", "admin", "pass", "Admin", 
                "admin@email.com", "admin", true);
        User staffUser = new User("USR002", "staff", "pass", "Staff", 
                "staff@email.com", "staff", true);
        
        assertTrue(adminUser.isAdmin());
        assertFalse(staffUser.isAdmin());
    }
    
    @Test
    public void testEquals() {
        User user1 = new User("USR001", "admin", "pass1", "Admin", 
                "admin@email.com", "admin", true);
        User user2 = new User("USR001", "staff", "pass2", "Staff", 
                "staff@email.com", "staff", false);
        assertTrue(user1.equals(user2));
    }
    
    @Test
    public void testHashCode() {
        User user1 = new User("USR001", "admin", "pass1", "Admin", 
                "admin@email.com", "admin", true);
        User user2 = new User("USR001", "staff", "pass2", "Staff", 
                "staff@email.com", "staff", false);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
    
    @Test
    public void testToString() {
        User user = new User("USR001", "admin", "pass", "Admin User", 
                "admin@email.com", "admin", true);
        String toString = user.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("USR001"));
        assertTrue(toString.contains("admin"));
    }
}

