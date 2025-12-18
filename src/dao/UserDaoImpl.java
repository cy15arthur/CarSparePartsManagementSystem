package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.DatabaseConnectionManager;

/**
 * Implementation of UserDao interface for managing user data access operations.
 * 
 * @author cyita
 */
public class UserDaoImpl implements UserDao {
    
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());
    
    @Override
    public User authenticate(String username, String password) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted authentication with empty credentials");
            return null;
        }
        
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND is_active = 1";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password); // In production, use hashed passwords
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getString("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFullName(resultSet.getString("full_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));
                    user.setActive(resultSet.getBoolean("is_active"));
                    
                    LOGGER.log(Level.INFO, "User authenticated successfully: {0}", username);
                    return user;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error authenticating user: " + username, e);
        }
        
        LOGGER.log(Level.WARNING, "Authentication failed for username: {0}", username);
        return null;
    }
    
    @Override
    public int create(User userObj) {
        if (userObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to create null user object");
            return 0;
        }
        
        String sql = "INSERT INTO users (user_id, username, password, full_name, email, role, is_active) "
                + "VALUES (?,?,?,?,?,?,?)";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, userObj.getUserId());
            preparedStatement.setString(2, userObj.getUsername());
            preparedStatement.setString(3, userObj.getPassword()); // In production, hash the password
            preparedStatement.setString(4, userObj.getFullName());
            preparedStatement.setString(5, userObj.getEmail());
            preparedStatement.setString(6, userObj.getRole());
            preparedStatement.setBoolean(7, userObj.isActive());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "User created successfully: {0}", userObj.getUsername());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating user: " + userObj.getUsername(), e);
            return 0;
        }
    }
    
    @Override
    public int update(User userObj) {
        if (userObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to update null user object");
            return 0;
        }
        
        String sql = "UPDATE users SET username=?, password=?, full_name=?, email=?, role=?, is_active=? "
                + "WHERE user_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, userObj.getUsername());
            preparedStatement.setString(2, userObj.getPassword());
            preparedStatement.setString(3, userObj.getFullName());
            preparedStatement.setString(4, userObj.getEmail());
            preparedStatement.setString(5, userObj.getRole());
            preparedStatement.setBoolean(6, userObj.isActive());
            preparedStatement.setString(7, userObj.getUserId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "User updated successfully: {0}", userObj.getUserId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user: " + userObj.getUserId(), e);
            return 0;
        }
    }
    
    @Override
    public User findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to search user with null or empty username");
            return null;
        }
        
        String sql = "SELECT * FROM users WHERE username = ?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, username);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getString("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFullName(resultSet.getString("full_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));
                    user.setActive(resultSet.getBoolean("is_active"));
                    
                    LOGGER.log(Level.FINE, "User found: {0}", username);
                    return user;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching user: " + username, e);
        }
        
        return null;
    }
    
    @Override
    public boolean usernameExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        String sql = "SELECT username FROM users WHERE username = ?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, username);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if username exists: " + username, e);
            return false;
        }
    }
}

