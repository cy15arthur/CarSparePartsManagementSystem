-- Database setup script for Users table
-- Run this script to create the users table for authentication

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) NOT NULL DEFAULT 'staff',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert default admin user
-- Username: admin
-- Password: admin123
-- NOTE: In production, passwords should be hashed (e.g., using BCrypt)
INSERT INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR001', 'admin', 'admin123', 'System Administrator', 'admin@carparts.com', 'admin', TRUE);

-- Insert sample staff user
-- Username: staff
-- Password: staff123
INSERT INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR002', 'staff', 'staff123', 'Staff Member', 'staff@carparts.com', 'staff', TRUE);

-- Verify the users were created
SELECT user_id, username, full_name, role, is_active FROM users;

