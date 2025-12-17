-- Test Database Setup Script
-- Creates a separate test database to avoid conflicts with production database

-- Create Test Database
CREATE DATABASE IF NOT EXISTS car_spare_parts_test_db;
USE car_spare_parts_test_db;

-- Drop existing tables if they exist (clean slate for testing)
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS parts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

-- Create Categories Table
CREATE TABLE categories (
    category_id VARCHAR(50) PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

-- Create Customers Table
CREATE TABLE customers (
    customer_id VARCHAR(50) PRIMARY KEY,
    customer_first_name VARCHAR(100) NOT NULL,
    customer_last_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100),
    phone VARCHAR(20)
);

-- Create Suppliers Table
CREATE TABLE suppliers (
    supplier_id VARCHAR(50) PRIMARY KEY,
    supplier_first_name VARCHAR(100) NOT NULL,
    contact_information VARCHAR(100),
    supplier_email VARCHAR(100),
    address VARCHAR(255)
);

-- Create Parts Table
CREATE TABLE parts (
    part_code VARCHAR(50) PRIMARY KEY,
    part_name VARCHAR(100) NOT NULL,
    brand VARCHAR(100),
    category_id VARCHAR(50),
    price DECIMAL(10, 2),
    stock_quantity DECIMAL(10, 2),
    supplier_id VARCHAR(50),
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE SET NULL
);

-- Create Sales Table
CREATE TABLE sales (
    sales_id VARCHAR(50) PRIMARY KEY,
    part_code VARCHAR(50),
    customer_id VARCHAR(50),
    sales_date DATETIME,
    quantity INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (part_code) REFERENCES parts(part_code) ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE SET NULL
);

-- Create Users Table (for authentication)
CREATE TABLE users (
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

-- Insert Default Admin User
INSERT INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR001', 'admin', 'admin123', 'System Administrator', 'admin@carparts.com', 'admin', TRUE);

-- Insert Default Staff User
INSERT INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR002', 'staff', 'staff123', 'Staff Member', 'staff@carparts.com', 'staff', TRUE);

-- Verify tables created
SHOW TABLES;

-- Success message
SELECT 'Test database setup completed successfully!' AS Status;
SELECT 'Database: car_spare_parts_test_db' AS Info;
SELECT 'You can now modify DatabaseConnectionManager to use this database for tests' AS NextStep;

