package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Part model class.
 * 
 * @author cyita
 */
public class PartTest {
    
    @Test
    public void testDefaultConstructor() {
        Part part = new Part();
        assertNotNull(part);
        assertNull(part.getPartCode());
    }
    
    @Test
    public void testParameterizedConstructor() {
        Part part = new Part("P001", "Brake Pad", "Bosch", "CAT001", 50.0, 100.0, "SUP001");
        
        assertEquals("P001", part.getPartCode());
        assertEquals("Brake Pad", part.getPartName());
        assertEquals("Bosch", part.getBrand());
        assertEquals(50.0, part.getPrice(), 0.01);
        assertEquals(100.0, part.getStockQuantity(), 0.01);
        assertEquals("CAT001", part.getCategoryId());
        assertEquals("SUP001", part.getSupplierId());
    }
    
    @Test
    public void testGettersAndSetters() {
        Part part = new Part();
        
        part.setPartCode("P002");
        part.setPartName("Oil Filter");
        part.setBrand("Fram");
        part.setPrice(25.0);
        part.setStockQuantity(50.0);
        part.setCategoryId("CAT002");
        part.setSupplierId("SUP002");
        
        assertEquals("P002", part.getPartCode());
        assertEquals("Oil Filter", part.getPartName());
        assertEquals("Fram", part.getBrand());
        assertEquals(25.0, part.getPrice(), 0.01);
        assertEquals(50.0, part.getStockQuantity(), 0.01);
        assertEquals("CAT002", part.getCategoryId());
        assertEquals("SUP002", part.getSupplierId());
    }
    
    @Test
    public void testEquals() {
        Part part1 = new Part("P001", "Brake Pad", "Bosch", "CAT001", 50.0, 100.0, "SUP001");
        Part part2 = new Part("P001", "Oil Filter", "Fram", "CAT002", 25.0, 50.0, "SUP002");
        assertTrue(part1.equals(part2));
    }
    
    @Test
    public void testHashCode() {
        Part part1 = new Part("P001", "Brake Pad", "Bosch", "CAT001", 50.0, 100.0, "SUP001");
        Part part2 = new Part("P001", "Oil Filter", "Fram", "CAT002", 25.0, 50.0, "SUP002");
        assertEquals(part1.hashCode(), part2.hashCode());
    }
    
    @Test
    public void testToString() {
        Part part = new Part("P001", "Brake Pad", "Bosch", "CAT001", 50.0, 100.0, "SUP001");
        String toString = part.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("P001"));
        assertTrue(toString.contains("Brake Pad"));
    }
}

