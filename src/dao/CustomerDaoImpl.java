package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import util.DatabaseConnectionManager;

/**
 * Implementation of CustomerDao interface for managing customer data access operations.
 * 
 * @author cyita
 */
public class CustomerDaoImpl implements CustomerDao {
    
    private static final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class.getName());
    @Override
    public int create(Customer customerObj) {
        if (customerObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to create null customer object");
            return 0;
        }
        
        String sql = "INSERT INTO customers (customer_id, customer_first_name, customer_last_name, "
                + "customer_email, phone) VALUES (?,?,?,?,?)";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, customerObj.getCustomerId());
            preparedStatement.setString(2, customerObj.getCustomerFirstName());
            preparedStatement.setString(3, customerObj.getCustomerLastName());
            preparedStatement.setString(4, customerObj.getCustomerEmail());
            preparedStatement.setString(5, customerObj.getCustomerPhone());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Customer created successfully: {0}", customerObj.getCustomerId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating customer: " + customerObj.getCustomerId(), e);
            return 0;
        }
    }

    @Override
    public int update(Customer customerObj) {
        if (customerObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to update null customer object");
            return 0;
        }
        
        String sql = "UPDATE customers SET customer_first_name=?, customer_last_name=?, "
                + "customer_email=?, phone=? WHERE customer_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, customerObj.getCustomerFirstName());
            preparedStatement.setString(2, customerObj.getCustomerLastName());
            preparedStatement.setString(3, customerObj.getCustomerEmail());
            preparedStatement.setString(4, customerObj.getCustomerPhone());
            preparedStatement.setString(5, customerObj.getCustomerId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Customer updated successfully: {0}", customerObj.getCustomerId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating customer: " + customerObj.getCustomerId(), e);
            return 0;
        }
    }

    @Override
    public int delete(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to delete customer with null or empty ID");
            return 0;
        }
        
        String sql = "DELETE FROM customers WHERE customer_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, customerId);
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Customer deleted successfully: {0}", customerId);
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting customer: " + customerId, e);
            return 0;
        }
    }

    @Override
    public Customer searchById(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to search customer with null or empty ID");
            return null;
        }
        
        String sql = "SELECT * FROM customers WHERE customer_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, customerId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getString("customer_id"));
                    customer.setCustomerFirstName(resultSet.getString("customer_first_name"));
                    customer.setCustomerLastName(resultSet.getString("customer_last_name"));
                    customer.setCustomerEmail(resultSet.getString("customer_email"));
                    customer.setCustomerPhone(resultSet.getString("phone"));
                    LOGGER.log(Level.FINE, "Customer found: {0}", customerId);
                    return customer;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching customer: " + customerId, e);
        }
        
        return null;
    }

    @Override
    public List<Customer> displayAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getString("customer_id"));
                customer.setCustomerFirstName(resultSet.getString("customer_first_name"));
                customer.setCustomerLastName(resultSet.getString("customer_last_name"));
                customer.setCustomerEmail(resultSet.getString("customer_email"));
                customer.setCustomerPhone(resultSet.getString("phone"));
                customers.add(customer);
            }
            
            LOGGER.log(Level.FINE, "Retrieved {0} customers", customers.size());
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all customers", e);
        }
        
        return customers;
    }
}
