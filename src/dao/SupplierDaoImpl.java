package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Supplier;
import util.DatabaseConnectionManager;

/**
 * Implementation of SupplierDao interface for managing supplier data access operations.
 * 
 * @author cyita
 */
public class SupplierDaoImpl implements SupplierDao {
    
    private static final Logger LOGGER = Logger.getLogger(SupplierDaoImpl.class.getName());

    @Override
    public int create(Supplier supplierObj) {
        if (supplierObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to create null supplier object");
            return 0;
        }
        
        String sql = "INSERT INTO suppliers (supplier_id, supplier_first_name, contact_information, "
                + "supplier_email, address) VALUES (?,?,?,?,?)";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, supplierObj.getSupplierId());
            preparedStatement.setString(2, supplierObj.getSupplierNames());
            preparedStatement.setString(3, supplierObj.getSuppliercontact());
            preparedStatement.setString(4, supplierObj.getSupplierEmail());
            preparedStatement.setString(5, supplierObj.getAddress());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Supplier created successfully: {0}", supplierObj.getSupplierId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating supplier: " + supplierObj.getSupplierId(), e);
            return 0;
        }
    }

    @Override
    public int update(Supplier supplierObj) {
        if (supplierObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to update null supplier object");
            return 0;
        }
        
        String sql = "UPDATE suppliers SET supplier_first_name=?, contact_information=?, "
                + "supplier_email=?, address=? WHERE supplier_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, supplierObj.getSupplierNames());
            preparedStatement.setString(2, supplierObj.getSuppliercontact());
            preparedStatement.setString(3, supplierObj.getSupplierEmail());
            preparedStatement.setString(4, supplierObj.getAddress());
            preparedStatement.setString(5, supplierObj.getSupplierId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Supplier updated successfully: {0}", supplierObj.getSupplierId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating supplier: " + supplierObj.getSupplierId(), e);
            return 0;
        }
    }

    @Override
    public int delete(String supplierId) {
        if (supplierId == null || supplierId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to delete supplier with null or empty ID");
            return 0;
        }
        
        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, supplierId);
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Supplier deleted successfully: {0}", supplierId);
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting supplier: " + supplierId, e);
            return 0;
        }
    }

    @Override
    public Supplier searchByCode(String supplierId) {
        if (supplierId == null || supplierId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to search supplier with null or empty ID");
            return null;
        }
        
        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, supplierId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(resultSet.getString("supplier_id"));
                    supplier.setSupplierNames(resultSet.getString("supplier_first_name"));
                    supplier.setSuppliercontact(resultSet.getString("contact_information"));
                    supplier.setSupplierEmail(resultSet.getString("supplier_email"));
                    supplier.setAddress(resultSet.getString("address"));
                    LOGGER.log(Level.FINE, "Supplier found: {0}", supplierId);
                    return supplier;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching supplier: " + supplierId, e);
        }
        
        return null;
    }

    @Override
    public List<Supplier> displayAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getString("supplier_id"));
                supplier.setSupplierNames(resultSet.getString("supplier_first_name"));
                supplier.setSuppliercontact(resultSet.getString("contact_information"));
                supplier.setSupplierEmail(resultSet.getString("supplier_email"));
                supplier.setAddress(resultSet.getString("address"));
                suppliers.add(supplier);
            }
            
            LOGGER.log(Level.FINE, "Retrieved {0} suppliers", suppliers.size());
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all suppliers", e);
        }
        
        return suppliers;
    }
}
