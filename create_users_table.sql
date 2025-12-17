-- Create Users Table for Authentication
-- Run this in MySQL if you don't have a users table

USE car_spare_parts_db;

-- Create Users Table (if doesn't exist)
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

-- Insert Default Admin User (only if doesn't exist)
INSERT IGNORE INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR001', 'admin', 'admin123', 'System Administrator', 'admin@carparts.com', 'admin', TRUE);

-- Insert Default Staff User (only if doesn't exist)
INSERT IGNORE INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR002', 'staff', 'staff123', 'Staff Member', 'staff@carparts.com', 'staff', TRUE);

-- Verify users table was created
SHOW TABLES LIKE 'users';

-- Check users were inserted
SELECT user_id, username, full_name, role, is_active FROM users;

-- Success message
SELECT 'Users table created successfully!' AS Status;

