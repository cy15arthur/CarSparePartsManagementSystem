# Test Database Setup Guide

## Problem
The existing database is being used elsewhere, causing conflicts. Solution: Create a separate test database.

---

## Step 1: Create Test Database

In MySQL, run:

```sql
source setup_test_database.sql
```

Or copy/paste the contents of `setup_test_database.sql` into MySQL.

This creates: `car_spare_parts_test_db`

---

## Step 2: Modify DatabaseConnectionManager for Testing

You have two options:

### Option A: Change Main Database (Temporary for Testing)

Edit `src/util/DatabaseConnectionManager.java`:

Change this line:
```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_db";
```

To:
```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_test_db";
```

**⚠️ Remember to change it back after testing!**

---

### Option B: Create Test-Specific Configuration (Better)

Create a separate configuration for tests. This way you don't need to change the main code.

#### Step 1: Create Test Database Connection Manager

Create `src/util/TestDatabaseConnectionManager.java`:

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test database connection manager.
 * Uses separate test database to avoid conflicts.
 */
public final class TestDatabaseConnectionManager {
    
    private static final Logger LOGGER = Logger.getLogger(TestDatabaseConnectionManager.class.getName());
    
    // Test database configuration
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_test_db";
    private static final String DB_USERNAME = "cyitatire";
    private static final String DB_PASSWORD = "cyitatire";
    
    private TestDatabaseConnectionManager() {
        throw new AssertionError("Cannot instantiate utility class");
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            LOGGER.log(Level.FINE, "Test database connection established");
            return connection;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish test database connection", e);
            throw e;
        }
    }
}
```

#### Step 2: Update DAO Tests to Use Test Database

Modify your DAO test files to use `TestDatabaseConnectionManager` instead of `DatabaseConnectionManager`.

**But wait - there's a better way!**

---

### Option C: Use System Property (Best for Testing)

Modify `DatabaseConnectionManager` to check for a test database property:

```java
// In DatabaseConnectionManager.java
private static final String JDBC_URL = System.getProperty("test.database.url", 
    "jdbc:mysql://localhost:3306/car_spare_parts_db");
```

Then in test setup, set the property. But this is complex.

---

## Recommended: Simple Approach

**Just change the database name temporarily for testing:**

1. **Edit** `src/util/DatabaseConnectionManager.java`
2. **Change** database name to `car_spare_parts_test_db`
3. **Run tests**
4. **Change back** to `car_spare_parts_db` when done

---

## Quick Steps

### 1. Create Test Database (in MySQL):
```sql
source setup_test_database.sql
```

### 2. Modify DatabaseConnectionManager.java:

Find this line (around line 20):
```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_db";
```

Change to:
```java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_spare_parts_test_db";
```

### 3. Save and Rebuild:
- Save the file
- Clean and Build project

### 4. Run Tests:
- Right-click `CustomerDaoTest.java` → Test File
- Tests should now pass!

### 5. Change Back (After Testing):
- Change database name back to `car_spare_parts_db`
- Save and rebuild

---

## Verify Test Database

After creating the test database, verify:

```sql
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

## Benefits of Separate Test Database

✅ No conflicts with production data
✅ Can drop/recreate tables safely
✅ Isolated test environment
✅ Can run tests anytime without affecting production

---

**Next:** Create the test database, then modify `DatabaseConnectionManager.java` to use it!

