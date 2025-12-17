package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for managing database connections.
 * Implements singleton pattern to ensure a single connection pool.
 * 
 * @author cyita
 */
public final class DatabaseConnectionManager {
    
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionManager.class.getName());
    
    // Database configuration constants
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_db";
    private static final String DB_USERNAME = "cyitatire";
    private static final String DB_PASSWORD = "cyitatire";
    
    // Private constructor to prevent instantiation
    private DatabaseConnectionManager() {
        throw new AssertionError("Cannot instantiate utility class");
    }
    
    /**
     * Creates and returns a new database connection.
     * 
     * @return a new Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            LOGGER.log(Level.FINE, "Database connection established successfully");
            return connection;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish database connection", e);
            throw e;
        }
    }
    
    /**
     * Closes a database connection safely.
     * 
     * @param connection the connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.FINE, "Database connection closed successfully");
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing database connection", e);
            }
        }
    }
}

