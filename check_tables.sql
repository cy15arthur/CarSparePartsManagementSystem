-- Quick script to check if your existing tables have the correct structure
-- Run this in MySQL to verify your tables match what the application needs

USE car_spare_parts_db;

-- Check customers table structure
DESCRIBE customers;

-- Check categories table structure  
DESCRIBE categories;

-- Check suppliers table structure
DESCRIBE suppliers;

-- Check parts table structure
DESCRIBE parts;

-- Check sales table structure
DESCRIBE sales;

-- Check if users table exists (for authentication)
SHOW TABLES LIKE 'users';

