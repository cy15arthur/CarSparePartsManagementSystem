package dao;

import model.Customer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 * Unit tests for CustomerDao implementation.
 * 
 * @author cyita
 */
public class CustomerDaoTest {
    
    private CustomerDao customerDao;
    private boolean databaseAvailable = false;
    
    @Before
    public void setUp() {
        customerDao = new CustomerDaoImpl();
        
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
    public void testCreateCustomer() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Clean up first in case test was run before
            customerDao.delete("TESTCUST001");
            
            Customer customer = new Customer("TESTCUST001", "Test", "Customer", 
                    "test@email.com", "1234567890");
            int result = customerDao.create(customer);
            
            if (result == 0) {
                fail("Customer creation returned 0. Check: 1) customers table exists, 2) No SQL errors in logs, 3) Database connection works");
            }
            
            assertTrue("Customer should be created (result=" + result + ")", result > 0);
            customerDao.delete("TESTCUST001");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage() + "\nStack trace: " + java.util.Arrays.toString(e.getStackTrace()));
        }
    }
    
    @Test
    public void testCreateNullCustomer() {
        int result = customerDao.create(null);
        assertEquals(0, result);
    }
    
    @Test
    public void testSearchById() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Clean up first
            customerDao.delete("TESTCUST002");
            
            Customer customer = new Customer("TESTCUST002", "John", "Doe", 
                    "john@test.com", "1234567890");
            int createResult = customerDao.create(customer);
            
            if (createResult == 0) {
                fail("Cannot test search - customer creation failed. Check customers table exists.");
            }
            
            Customer found = customerDao.searchById("TESTCUST002");
            assertNotNull("Customer should be found after creation", found);
            assertEquals("TESTCUST002", found.getCustomerId());
            
            customerDao.delete("TESTCUST002");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testUpdateCustomer() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Clean up first
            customerDao.delete("TESTCUST003");
            
            Customer customer = new Customer("TESTCUST003", "Jane", "Smith", 
                    "jane@test.com", "0987654321");
            int createResult = customerDao.create(customer);
            
            if (createResult == 0) {
                fail("Cannot test update - customer creation failed. Check customers table exists.");
            }
            
            customer.setCustomerFirstName("Updated");
            int result = customerDao.update(customer);
            
            if (result == 0) {
                fail("Update returned 0. Check: 1) customers table exists, 2) No SQL errors, 3) Customer exists in database");
            }
            
            assertTrue("Update should succeed (result=" + result + ")", result > 0);
            
            customerDao.delete("TESTCUST003");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testDeleteCustomer() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            // Clean up first
            customerDao.delete("TESTCUST004");
            
            Customer customer = new Customer("TESTCUST004", "Delete", "Test", 
                    "delete@test.com", "1111111111");
            int createResult = customerDao.create(customer);
            
            if (createResult == 0) {
                fail("Cannot test delete - customer creation failed. Check customers table exists.");
            }
            
            int result = customerDao.delete("TESTCUST004");
            
            if (result == 0) {
                fail("Delete returned 0. Check: 1) customers table exists, 2) No SQL errors, 3) Customer exists in database");
            }
            
            assertTrue("Delete should succeed (result=" + result + ")", result > 0);
            
            Customer deleted = customerDao.searchById("TESTCUST004");
            assertNull("Deleted customer should not be found", deleted);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testDisplayAllCustomers() {
        if (!databaseAvailable) {
            System.out.println("Skipping test - database not available");
            return;
        }
        
        try {
            java.util.List<Customer> customers = customerDao.displayAllCustomers();
            assertNotNull(customers);
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}

