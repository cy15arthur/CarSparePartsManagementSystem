package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

/**
 * Unit tests for Sales model class.
 * 
 * @author cyita
 */
public class SalesTest {
    
    @Test
    public void testDefaultConstructor() {
        Sales sales = new Sales();
        assertNotNull(sales);
        assertNull(sales.getSalesId());
    }
    
    @Test
    public void testParameterizedConstructor() {
        LocalDateTime date = LocalDateTime.now();
        Sales sales = new Sales("SALE001", "P001", "CUST001", 5, 250.0, date);
        
        assertEquals("SALE001", sales.getSalesId());
        assertEquals("P001", sales.getPartCode());
        assertEquals("CUST001", sales.getCustomerId());
        assertEquals(date, sales.getSaleDate());
        assertEquals(5, sales.getQuantity());
        assertEquals(250.0, sales.getTotalPrice(), 0.01);
    }
    
    @Test
    public void testGettersAndSetters() {
        Sales sales = new Sales();
        LocalDateTime date = LocalDateTime.of(2024, 1, 15, 10, 30);
        
        sales.setSalesId("SALE002");
        sales.setPartCode("P002");
        sales.setCustomerId("CUST002");
        sales.setSaleDate(date);
        sales.setQuantity(10);
        sales.setTotalPrice(500.0);
        
        assertEquals("SALE002", sales.getSalesId());
        assertEquals("P002", sales.getPartCode());
        assertEquals("CUST002", sales.getCustomerId());
        assertEquals(date, sales.getSaleDate());
        assertEquals(10, sales.getQuantity());
        assertEquals(500.0, sales.getTotalPrice(), 0.01);
    }
    
    @Test
    public void testEquals() {
        LocalDateTime date = LocalDateTime.now();
        Sales sales1 = new Sales("SALE001", "P001", "CUST001", 5, 250.0, date);
        Sales sales2 = new Sales("SALE001", "P002", "CUST002", 10, 500.0, date);
        assertTrue(sales1.equals(sales2));
    }
    
    @Test
    public void testHashCode() {
        LocalDateTime date = LocalDateTime.now();
        Sales sales1 = new Sales("SALE001", "P001", "CUST001", 5, 250.0, date);
        Sales sales2 = new Sales("SALE001", "P002", "CUST002", 10, 500.0, date);
        assertEquals(sales1.hashCode(), sales2.hashCode());
    }
    
    @Test
    public void testToString() {
        LocalDateTime date = LocalDateTime.now();
        Sales sales = new Sales("SALE001", "P001", "CUST001", 5, 250.0, date);
        String toString = sales.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("SALE001"));
    }
}

