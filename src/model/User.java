package model;

import java.util.Objects;

/**
 * Represents a User entity for authentication and authorization in the system.
 * 
 * @author cyita
 */
public class User {
    
    private String userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role; // "admin", "manager", "staff"
    private boolean isActive;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param userId the unique identifier for the user
     * @param username the username for login
     * @param password the password (should be hashed in production)
     * @param fullName the full name of the user
     * @param email the email address
     * @param role the role of the user (admin, manager, staff)
     * @param isActive whether the user account is active
     */
    public User(String userId, String username, String password, String fullName, 
            String email, String role, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }

    /**
     * Gets the user ID.
     * 
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * 
     * @param userId the user ID to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the full name.
     * 
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name.
     * 
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the email.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role.
     * 
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     * 
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Checks if the user is active.
     * 
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status.
     * 
     * @param isActive the active status to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Checks if the user is an admin.
     * 
     * @return true if admin, false otherwise
     */
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }

    @Override
    public String toString() {
        return "User{"
                + "userId='" + userId + '\''
                + ", username='" + username + '\''
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", role='" + role + '\''
                + ", isActive=" + isActive
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

