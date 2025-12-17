package dao;

import model.User;

/**
 * Data Access Object interface for User entity operations.
 * Provides methods for authentication and user management.
 * 
 * @author cyita
 */
public interface UserDao {
    
    /**
     * Authenticates a user by username and password.
     * 
     * @param username the username
     * @param password the password
     * @return the User object if authentication is successful, null otherwise
     */
    User authenticate(String username, String password);
    
    /**
     * Creates a new user in the database.
     * 
     * @param userObj the user object to create
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int create(User userObj);
    
    /**
     * Updates an existing user in the database.
     * 
     * @param userObj the user object with updated information
     * @return the number of rows affected (1 if successful, 0 otherwise)
     */
    int update(User userObj);
    
    /**
     * Searches for a user by username.
     * 
     * @param username the username to search for
     * @return the User object if found, null otherwise
     */
    User findByUsername(String username);
    
    /**
     * Checks if a username already exists.
     * 
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    boolean usernameExists(String username);
}

