package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Supplier model class.
 * 
 * @author cyita
 */
public class SupplierTest {
    
    @Test
    public void testDefaultConstructor() {
        Supplier supplier = new Supplier();
        assertNotNull(supplier);
        assertNull(supplier.getSupplierId());
    }
    
    @Test
    public void testParameterizedConstructor() {
        Supplier supplier = new Supplier("SUP001", "ABC Parts", "123-456-7890", 
                "abc@parts.com", "123 Main St");
        
        assertEquals("SUP001", supplier.getSupplierId());
        assertEquals("ABC Parts", supplier.getSupplierNames());
        assertEquals("123-456-7890", supplier.getSuppliercontact());
        assertEquals("abc@parts.com", supplier.getSupplierEmail());
        assertEquals("123 Main St", supplier.getAddress());
    }
    
    @Test
    public void testGettersAndSetters() {
        Supplier supplier = new Supplier();
        
        supplier.setSupplierId("SUP002");
        supplier.setSupplierNames("XYZ Auto");
        supplier.setSuppliercontact("098-765-4321");
        supplier.setSupplierEmail("xyz@auto.com");
        supplier.setAddress("456 Oak Ave");
        
        assertEquals("SUP002", supplier.getSupplierId());
        assertEquals("XYZ Auto", supplier.getSupplierNames());
        assertEquals("098-765-4321", supplier.getSuppliercontact());
        assertEquals("xyz@auto.com", supplier.getSupplierEmail());
        assertEquals("456 Oak Ave", supplier.getAddress());
    }
    
    @Test
    public void testEquals() {
        Supplier supplier1 = new Supplier("SUP001", "ABC Parts", "123-456-7890", 
                "abc@parts.com", "123 Main St");
        Supplier supplier2 = new Supplier("SUP001", "XYZ Auto", "098-765-4321", 
                "xyz@auto.com", "456 Oak Ave");
        assertTrue(supplier1.equals(supplier2));
    }
    
    @Test
    public void testHashCode() {
        Supplier supplier1 = new Supplier("SUP001", "ABC Parts", "123-456-7890", 
                "abc@parts.com", "123 Main St");
        Supplier supplier2 = new Supplier("SUP001", "XYZ Auto", "098-765-4321", 
                "xyz@auto.com", "456 Oak Ave");
        assertEquals(supplier1.hashCode(), supplier2.hashCode());
    }
    
    @Test
    public void testToString() {
        Supplier supplier = new Supplier("SUP001", "ABC Parts", "123-456-7890", 
                "abc@parts.com", "123 Main St");
        String toString = supplier.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("SUP001"));
        assertTrue(toString.contains("ABC Parts"));
    }
}

