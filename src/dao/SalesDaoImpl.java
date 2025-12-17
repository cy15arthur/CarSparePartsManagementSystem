package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sales;
import util.DatabaseConnectionManager;

/**
 * Implementation of SalesDao interface for managing sales data access operations.
 * 
 * @author cyita
 */
public class SalesDaoImpl implements SalesDao {
    
    private static final Logger LOGGER = Logger.getLogger(SalesDaoImpl.class.getName());
    @Override
    public int create(Sales salesObj) {
        if (salesObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to create null sales object");
            return 0;
        }
        
        if (salesObj.getSaleDate() == null) {
            LOGGER.log(Level.WARNING, "Attempted to create sales with null sale date");
            return 0;
        }
        
        String sql = "INSERT INTO sales (sales_id, part_code, customer_id, quantity, total_price, "
                + "sale_date) VALUES(?,?,?,?,?,?)";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, salesObj.getSalesId());
            preparedStatement.setString(2, salesObj.getPartCode());
            preparedStatement.setString(3, salesObj.getCustomerId());
            preparedStatement.setInt(4, salesObj.getQuantity());
            preparedStatement.setDouble(5, salesObj.getTotalPrice());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(salesObj.getSaleDate()));
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Sales record created successfully: {0}", salesObj.getSalesId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating sales record: " + salesObj.getSalesId(), e);
            return 0;
        }
    }

    @Override
    public Sales searchById(String salesId) {
        if (salesId == null || salesId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to search sales with null or empty ID");
            return null;
        }
        
        String sql = "SELECT * FROM sales WHERE sales_id = ?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, salesId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Sales sales = new Sales();
                    sales.setSalesId(resultSet.getString("sales_id"));
                    sales.setPartCode(resultSet.getString("part_code"));
                    sales.setCustomerId(resultSet.getString("customer_id"));
                    sales.setQuantity(resultSet.getInt("quantity"));
                    sales.setTotalPrice(resultSet.getDouble("total_price"));
                    
                    Timestamp timestamp = resultSet.getTimestamp("sale_date");
                    if (timestamp != null) {
                        sales.setSaleDate(timestamp.toLocalDateTime());
                    }
                    
                    LOGGER.log(Level.FINE, "Sales record found: {0}", salesId);
                    return sales;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching sales record: " + salesId, e);
        }
        
        return null;
    }

    @Override
    public List<Sales> displayAllSales() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Sales sales = new Sales();
                sales.setSalesId(resultSet.getString("sales_id"));
                sales.setPartCode(resultSet.getString("part_code"));
                sales.setCustomerId(resultSet.getString("customer_id"));
                sales.setQuantity(resultSet.getInt("quantity"));
                sales.setTotalPrice(resultSet.getDouble("total_price"));
                
                Timestamp timestamp = resultSet.getTimestamp("sale_date");
                if (timestamp != null) {
                    sales.setSaleDate(timestamp.toLocalDateTime());
                }
                
                salesList.add(sales);
            }
            
            LOGGER.log(Level.FINE, "Retrieved {0} sales records", salesList.size());
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all sales records", e);
        }
        
        return salesList;
    }
}
