package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Customer model class.
 * 
 * @author cyita
 */
public class CustomerTest {
    
    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();
        assertNotNull("Customer object should be created", customer);
        assertNull("Customer ID should be null initially", customer.getCustomerId());
    }
    
    @Test
    public void testParameterizedConstructor() {
        Customer customer = new Customer("CUST001", "John", "Doe", "john@email.com", "1234567890");
        
        assertEquals("CUST001", customer.getCustomerId());
        assertEquals("John", customer.getCustomerFirstName());
        assertEquals("Doe", customer.getCustomerLastName());
        assertEquals("john@email.com", customer.getCustomerEmail());
        assertEquals("1234567890", customer.getCustomerPhone());
    }
    
    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();
        
        customer.setCustomerId("CUST002");
        customer.setCustomerFirstName("Jane");
        customer.setCustomerLastName("Smith");
        customer.setCustomerEmail("jane@email.com");
        customer.setCustomerPhone("0987654321");
        
        assertEquals("CUST002", customer.getCustomerId());
        assertEquals("Jane", customer.getCustomerFirstName());
        assertEquals("Smith", customer.getCustomerLastName());
        assertEquals("jane@email.com", customer.getCustomerEmail());
        assertEquals("0987654321", customer.getCustomerPhone());
    }
    
    @Test
    public void testEquals_SameObject() {
        Customer customer = new Customer("CUST001", "John", "Doe", "john@email.com", "1234567890");
        assertTrue(customer.equals(customer));
    }
    
    @Test
    public void testEquals_EqualObjects() {
        Customer customer1 = new Customer("CUST001", "John", "Doe", "john@email.com", "1234567890");
        Customer customer2 = new Customer("CUST001", "Jane", "Smith", "jane@email.com", "0987654321");
        assertTrue("Customers with same ID should be equal", customer1.equals(customer2));
    }
    
    @Test
    public void testEquals_DifferentObjects() {
        Customer customer1 = new Customer("CUST001", "John", "Doe", "john@email.com", "1234567890");
        Customer customer2 = new Customer("CUST002", "John", "Doe", "john@email.com", "1234567890");
        assertFalse("Customers with different IDs should not be equal", customer1.equals(customer2));
    }
    
    @Test
    public void testHashCode() {
        Customer customer1 = new Customer("CUST001", "John", "Doe", "john@email.com", "1234567890");
        Customer customer2 = new Customer("CUST001", "Jane", "Smith", "jane@email.com", "0987654321");
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }
    
    @Test
    public void testToString() {
        Customer customer = new Customer("CUST001", "John", "Doe", "john@email.com", "1234567890");
        String toString = customer.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("CUST001"));
        assertTrue(toString.contains("John"));
    }
}

