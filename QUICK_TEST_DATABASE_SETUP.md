# Quick Test Database Setup

## Step-by-Step Instructions

### Step 1: Create Test Database in MySQL

Since you're already in MySQL, run:

```sql
source setup_test_database.sql
```

Or copy/paste the contents of `setup_test_database.sql` into MySQL.

**This creates:** `car_spare_parts_test_db` with all required tables.

---

### Step 2: DatabaseConnectionManager Already Updated! ✅

I've already modified `DatabaseConnectionManager.java` to use the test database:
- Changed from: `car_spare_parts_db`
- Changed to: `car_spare_parts_test_db`

**No further changes needed!**

---

### Step 3: Verify Test Database

In MySQL, verify the database was created:

```sql
SHOW DATABASES LIKE 'car_spare_parts_test_db';
USE car_spare_parts_test_db;
SHOW TABLES;
```

Should show:
- categories
- customers
- suppliers
- parts
- sales
- users

---

### Step 4: Clean and Build Project

In NetBeans:
1. Right-click project → **Clean and Build**
2. Wait for build to complete

---

### Step 5: Run Tests

1. Right-click `CustomerDaoTest.java` → **Test File**
2. Tests should now pass! ✅

---

## What Changed

**File Modified:** `src/util/DatabaseConnectionManager.java`

**Change:**
- Database name: `car_spare_parts_db` → `car_spare_parts_test_db`

**Result:**
- All DAO operations now use the test database
- No conflicts with production database
- Tests can run safely

---

## To Switch Back to Production Database

When you're done testing, change it back:

In `DatabaseConnectionManager.java`, change:
```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_test_db";
```

Back to:
```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_db";
```

---

## Summary

1. ✅ **Create test database:** `source setup_test_database.sql` (in MySQL)
2. ✅ **DatabaseConnectionManager updated** (already done)
3. ✅ **Clean and Build** project
4. ✅ **Run tests** - should pass now!

---

**Next:** Create the test database in MySQL, then run your tests!

