# Quick Fix: DAO Tests Failing

## Problem
DAO tests are failing with `AssertionFailedError` because database operations return 0 (no rows affected).

**Root Cause:** Database tables don't exist!

---

## Quick Fix (3 Steps)

### Step 1: Run Database Setup Script

**Option A: MySQL Command Line**
```bash
mysql -u cyitatire -p car_spare_parts_db < setup_database.sql
```

**Option B: MySQL Workbench**
1. Open MySQL Workbench
2. Connect to your database
3. File → Open SQL Script → Select `setup_database.sql`
4. Click Execute (⚡ icon)

**Option C: NetBeans**
1. Services → Databases
2. Right-click MySQL connection → Execute Command
3. Copy/paste contents of `setup_database.sql`
4. Click Run (▶ icon)

---

### Step 2: Verify Tables Created

Run this SQL:
```sql
USE car_spare_parts_db;
SHOW TABLES;
```

You should see:
- ✅ categories
- ✅ customers
- ✅ suppliers
- ✅ parts
- ✅ sales
- ✅ users

---

### Step 3: Run Tests Again

1. **Clean and Build** project
2. **Run tests:**
   - Right-click `CustomerDaoTest.java` → Test File
3. **Tests should now pass!** ✅

---

## What the Script Does

1. ✅ Creates database `car_spare_parts_db`
2. ✅ Creates all 6 required tables
3. ✅ Sets up foreign key relationships
4. ✅ Inserts default admin and staff users
5. ✅ Verifies everything is created

---

## If Tests Still Fail

### Check Database Connection:
- MySQL service running?
- Credentials correct in `DatabaseConnectionManager.java`?
- Database name matches?

### Check Error Messages:
- Look at Test Results window
- Click on failed test to see detailed error
- Check for SQL syntax errors

### Check Logs:
- DAO methods log errors to console
- Look for SQLException messages
- Check what SQL is being executed

---

## Expected Results After Fix

- ✅ `testCreateNullCustomer` - Should pass (doesn't need database)
- ✅ `testCreateCustomer` - Should pass (creates customer)
- ✅ `testSearchById` - Should pass (searches customer)
- ✅ `testUpdateCustomer` - Should pass (updates customer)
- ✅ `testDeleteCustomer` - Should pass (deletes customer)
- ✅ `testDisplayAllCustomers` - Should pass (lists customers)

---

**The main issue is missing database tables. Once you run `setup_database.sql`, tests should pass!**

