-- Safe Database Setup Script (Does NOT drop existing tables)
-- Use this if you want to keep your existing data

USE car_spare_parts_db;

-- Create Categories Table (if doesn't exist)
CREATE TABLE IF NOT EXISTS categories (
    category_id VARCHAR(50) PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

-- Create Customers Table (if doesn't exist)
CREATE TABLE IF NOT EXISTS customers (
    customer_id VARCHAR(50) PRIMARY KEY,
    customer_first_name VARCHAR(100) NOT NULL,
    customer_last_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100),
    phone VARCHAR(20)
);

-- Create Suppliers Table (if doesn't exist)
CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id VARCHAR(50) PRIMARY KEY,
    supplier_first_name VARCHAR(100) NOT NULL,
    contact_information VARCHAR(100),
    supplier_email VARCHAR(100),
    address VARCHAR(255)
);

-- Create Parts Table (if doesn't exist)
CREATE TABLE IF NOT EXISTS parts (
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

-- Create Sales Table (if doesn't exist)
CREATE TABLE IF NOT EXISTS sales (
    sales_id VARCHAR(50) PRIMARY KEY,
    part_code VARCHAR(50),
    customer_id VARCHAR(50),
    sales_date DATETIME,
    quantity INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (part_code) REFERENCES parts(part_code) ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE SET NULL
);

-- Create Users Table (if doesn't exist) - for authentication
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

-- Verify tables
SHOW TABLES;

SELECT 'Database setup completed (safe mode - existing tables preserved)!' AS Status;

