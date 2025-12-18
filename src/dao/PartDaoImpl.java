package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Part;
import util.DatabaseConnectionManager;

/**
 * Implementation of PartDao interface for managing part data access operations.
 * 
 * @author cyita
 */
public class PartDaoImpl implements PartDao {
    
    private static final Logger LOGGER = Logger.getLogger(PartDaoImpl.class.getName());
    
    
    @Override
    public int create(Part partObj) {
        if (partObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to create null part object");
            return 0;
        }
        
        String sql = "INSERT INTO parts (part_code, part_name, brand, category_id, price, "
                + "stock_quantity, supplier_id) VALUES (?,?,?,?,?,?,?)";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, partObj.getPartCode());
            preparedStatement.setString(2, partObj.getPartName());
            preparedStatement.setString(3, partObj.getBrand());
            preparedStatement.setString(4, partObj.getCategoryId());
            preparedStatement.setDouble(5, partObj.getPrice());
            preparedStatement.setDouble(6, partObj.getStockQuantity());
            preparedStatement.setString(7, partObj.getSupplierId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Part created successfully: {0}", partObj.getPartCode());
            return rowsAffected;
            
        } catch (SQLException e) {
            // Log concise message only; UI will tell user what went wrong
            LOGGER.log(Level.WARNING, "Error creating part {0}: {1}",
                    new Object[]{partObj.getPartCode(), e.getMessage()});
            return 0;
        }
    }

    @Override
    public int update(Part partObj) {
        if (partObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to update null part object");
            return 0;
        }
        
        String sql = "UPDATE parts SET part_name=?, brand=?, category_id=?, price=?, "
                + "stock_quantity=?, supplier_id=? WHERE part_code=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, partObj.getPartName());
            preparedStatement.setString(2, partObj.getBrand());
            preparedStatement.setString(3, partObj.getCategoryId());
            preparedStatement.setDouble(4, partObj.getPrice());
            preparedStatement.setDouble(5, partObj.getStockQuantity());
            preparedStatement.setString(6, partObj.getSupplierId());
            preparedStatement.setString(7, partObj.getPartCode());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Part updated successfully: {0}", partObj.getPartCode());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating part: " + partObj.getPartCode(), e);
            return 0;
        }
    }
    @Override
    public boolean exists(String partCode) {
        if (partCode == null || partCode.trim().isEmpty()) {
            return false;
        }
        
        String sql = "SELECT part_code FROM parts WHERE part_code = ?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, partCode);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if part exists: " + partCode, e);
            return false;
        }
    }

    @Override
    public int delete(String partCode) {
        if (partCode == null || partCode.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to delete part with null or empty code");
            return 0;
        }
        
        String sql = "DELETE FROM parts WHERE part_code=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, partCode);
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Part deleted successfully: {0}", partCode);
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting part: " + partCode, e);
            return 0;
        }
    }

    @Override
    public Part searchByCode(String partCode) {
        if (partCode == null || partCode.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to search part with null or empty code");
            return null;
        }
        
        String sql = "SELECT * FROM parts WHERE part_code=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, partCode);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Part part = new Part();
                    part.setPartCode(resultSet.getString("part_code"));
                    part.setPartName(resultSet.getString("part_name"));
                    part.setBrand(resultSet.getString("brand"));
                    part.setCategoryId(resultSet.getString("category_id"));
                    part.setPrice(resultSet.getDouble("price"));
                    part.setStockQuantity(resultSet.getDouble("stock_quantity"));
                    part.setSupplierId(resultSet.getString("supplier_id"));
                    LOGGER.log(Level.FINE, "Part found: {0}", partCode);
                    return part;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching part: " + partCode, e);
        }
        
        return null;
    }

    @Override
    public List<Part> displayAllParts() {
        List<Part> parts = new ArrayList<>();
        String sql = "SELECT * FROM parts";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Part part = new Part();
                part.setPartCode(resultSet.getString("part_code"));
                part.setPartName(resultSet.getString("part_name"));
                part.setBrand(resultSet.getString("brand"));
                part.setCategoryId(resultSet.getString("category_id"));
                part.setPrice(resultSet.getDouble("price"));
                part.setStockQuantity(resultSet.getDouble("stock_quantity"));
                part.setSupplierId(resultSet.getString("supplier_id"));
                parts.add(part);
            }
            
            LOGGER.log(Level.FINE, "Retrieved {0} parts", parts.size());
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all parts", e);
        }
        
        return parts;
    }
}