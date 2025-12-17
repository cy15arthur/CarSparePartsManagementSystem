# Database Setup for Tests

## Problem: DAO Tests Failing

DAO tests are failing because the **database tables don't exist** or there are **SQL errors**.

Error: `AssertionFailedError` - Database operations return 0 (no rows affected)

---

## Solution: Create Database Tables

### Step 1: Check Database Connection

1. **Verify MySQL is running**
2. **Check credentials** in `src/util/DatabaseConnectionManager.java`:
   - Database: `car_spare_parts_db`
   - Username: `cyitatire`
   - Password: `cyitatire`

### Step 2: Create Database (if needed)

```sql
CREATE DATABASE IF NOT EXISTS car_spare_parts_db;
USE car_spare_parts_db;
```

### Step 3: Create Customers Table

```sql
CREATE TABLE IF NOT EXISTS customers (
    customer_id VARCHAR(50) PRIMARY KEY,
    customer_first_name VARCHAR(100) NOT NULL,
    customer_last_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100),
    phone VARCHAR(20)
);
```

### Step 4: Create Other Required Tables

#### Categories Table:
```sql
CREATE TABLE IF NOT EXISTS categories (
    category_id VARCHAR(50) PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);
```

#### Parts Table:
```sql
CREATE TABLE IF NOT EXISTS parts (
    part_code VARCHAR(50) PRIMARY KEY,
    part_name VARCHAR(100) NOT NULL,
    brand VARCHAR(100),
    category_id VARCHAR(50),
    price DECIMAL(10, 2),
    stock_quantity DECIMAL(10, 2),
    supplier_id VARCHAR(50),
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);
```

#### Suppliers Table:
```sql
CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id VARCHAR(50) PRIMARY KEY,
    supplier_first_name VARCHAR(100) NOT NULL,
    contact_information VARCHAR(100),
    supplier_email VARCHAR(100),
    address VARCHAR(255)
);
```

#### Sales Table:
```sql
CREATE TABLE IF NOT EXISTS sales (
    sales_id VARCHAR(50) PRIMARY KEY,
    part_code VARCHAR(50),
    customer_id VARCHAR(50),
    sales_date DATETIME,
    quantity INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (part_code) REFERENCES parts(part_code),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

#### Users Table (for authentication):
```sql
CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) NOT NULL DEFAULT 'staff',
    is_active BOOLEAN DEFAULT TRUE
);
```

---

## Quick Setup Script

Save this as `setup_database.sql` and run it:

```sql
-- Create Database
CREATE DATABASE IF NOT EXISTS car_spare_parts_db;
USE car_spare_parts_db;

-- Create Tables
CREATE TABLE IF NOT EXISTS categories (
    category_id VARCHAR(50) PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    customer_id VARCHAR(50) PRIMARY KEY,
    customer_first_name VARCHAR(100) NOT NULL,
    customer_last_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id VARCHAR(50) PRIMARY KEY,
    supplier_first_name VARCHAR(100) NOT NULL,
    contact_information VARCHAR(100),
    supplier_email VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS parts (
    part_code VARCHAR(50) PRIMARY KEY,
    part_name VARCHAR(100) NOT NULL,
    brand VARCHAR(100),
    category_id VARCHAR(50),
    price DECIMAL(10, 2),
    stock_quantity DECIMAL(10, 2),
    supplier_id VARCHAR(50),
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE IF NOT EXISTS sales (
    sales_id VARCHAR(50) PRIMARY KEY,
    part_code VARCHAR(50),
    customer_id VARCHAR(50),
    sales_date DATETIME,
    quantity INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (part_code) REFERENCES parts(part_code),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) NOT NULL DEFAULT 'staff',
    is_active BOOLEAN DEFAULT TRUE
);

-- Verify tables created
SHOW TABLES;
```

---

## How to Run SQL Script

### Option 1: MySQL Command Line
```bash
mysql -u cyitatire -p < setup_database.sql
```

### Option 2: MySQL Workbench
1. Open MySQL Workbench
2. Connect to your database
3. File → Open SQL Script
4. Select `setup_database.sql`
5. Click Execute (⚡ icon)

### Option 3: NetBeans Database Tools
1. Services → Databases
2. Right-click MySQL connection → Execute Command
3. Paste SQL script
4. Click Run (▶ icon)

---

## Verify Tables Exist

```sql
USE car_spare_parts_db;
SHOW TABLES;
```

You should see:
- categories
- customers
- parts
- suppliers
- sales
- users

---

## After Setup: Run Tests Again

1. **Clean and Build** project
2. **Run tests** again:
   - Right-click `CustomerDaoTest.java` → Test File
3. **Tests should pass** if tables exist

---

## Troubleshooting

### Issue: "Table doesn't exist"
**Solution:** Run the SQL script to create tables

### Issue: "Access denied"
**Solution:** Check database credentials in `DatabaseConnectionManager.java`

### Issue: "Connection refused"
**Solution:** 
- Check MySQL service is running
- Verify port 3306 is correct
- Check firewall settings

### Issue: "Foreign key constraint fails"
**Solution:** 
- Create tables in order: categories, suppliers, customers, parts, sales
- Or remove foreign key constraints temporarily for testing

---

## Test Data (Optional)

After creating tables, you can add test data:

```sql
INSERT INTO categories VALUES ('CAT001', 'Engine Parts');
INSERT INTO customers VALUES ('CUST001', 'John', 'Doe', 'john@email.com', '123-456-7890');
```

---

**Once tables are created, DAO tests should pass!**

