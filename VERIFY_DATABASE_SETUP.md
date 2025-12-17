# Verify Database Setup - Next Steps

## You've Checked Your Tables - Now What?

Since you did Option A (checked existing tables), follow these steps:

---

## Step 1: Create Users Table (If Missing)

If you don't have a `users` table for authentication, run this in MySQL:

```sql
USE car_spare_parts_db;

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

INSERT IGNORE INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR001', 'admin', 'admin123', 'System Administrator', 'admin@carparts.com', 'admin', TRUE);

INSERT IGNORE INTO users (user_id, username, password, full_name, email, role, is_active)
VALUES ('USR002', 'staff', 'staff123', 'Staff Member', 'staff@carparts.com', 'staff', TRUE);
```

**Or use the file:**
```sql
source create_users_table.sql
```

---

## Step 2: Verify Table Structures Match

Run these commands to verify your tables have the correct columns:

### Check Customers Table:
```sql
DESCRIBE customers;
```
**Should have:**
- customer_id (PRIMARY KEY)
- customer_first_name
- customer_last_name
- customer_email
- phone

### Check Categories Table:
```sql
DESCRIBE categories;
```
**Should have:**
- category_id (PRIMARY KEY)
- category_name

### Check Suppliers Table:
```sql
DESCRIBE suppliers;
```
**Should have:**
- supplier_id (PRIMARY KEY)
- supplier_first_name
- contact_information
- supplier_email
- address

### Check Parts Table:
```sql
DESCRIBE parts;
```
**Should have:**
- part_code (PRIMARY KEY)
- part_name
- brand
- category_id
- price
- stock_quantity
- supplier_id

### Check Sales Table:
```sql
DESCRIBE sales;
```
**Should have:**
- sales_id (PRIMARY KEY)
- part_code
- customer_id
- sales_date
- quantity
- total_price

---

## Step 3: If Column Names Don't Match

If your tables have different column names, you have two options:

### Option 1: Alter Tables (Keep Data)
```sql
-- Example: If your customers table uses 'id' instead of 'customer_id'
ALTER TABLE customers CHANGE id customer_id VARCHAR(50);
```

### Option 2: Update DAO Code
Modify the DAO implementations to match your existing column names.

---

## Step 4: Test Database Connection

In NetBeans, test if your application can connect:

1. Run the application
2. Try to create a customer
3. Check if it works

---

## Step 5: Run Tests Again

Once tables are verified:

1. **Clean and Build** project in NetBeans
2. **Run tests:**
   - Right-click `CustomerDaoTest.java` → Test File
3. **Tests should pass** if tables have correct structure

---

## Quick Verification Checklist

- [ ] `customers` table exists with correct columns
- [ ] `categories` table exists with correct columns
- [ ] `suppliers` table exists with correct columns
- [ ] `parts` table exists with correct columns
- [ ] `sales` table exists with correct columns
- [ ] `users` table exists (for authentication)
- [ ] Database connection works
- [ ] Tests run successfully

---

## Common Issues

### Issue: Column names don't match
**Example:** Your table has `id` but code expects `customer_id`
**Solution:** Either alter table or update DAO code

### Issue: Missing columns
**Solution:** Add missing columns with `ALTER TABLE`

### Issue: Wrong data types
**Solution:** Check if types match (VARCHAR, DECIMAL, INT, etc.)

---

## If Everything Matches

If your existing tables have the correct structure:
1. ✅ Just create `users` table (if missing)
2. ✅ Run tests
3. ✅ Tests should pass!

---

**Next:** Create the `users` table if you don't have it, then run your tests!

