# Test Troubleshooting Guide

## Common Test Failure Issues and Solutions

### Issue 1: Database Connection Errors

**Symptoms:**
- Tests fail with `SQLException`
- "Communications link failure"
- "Access denied"

**Solutions:**

#### Check Database is Running:
```bash
# Check MySQL service
# Windows: Services → MySQL
# Or check in MySQL Workbench
```

#### Verify Database Credentials:
- Check `src/util/DatabaseConnectionManager.java`
- Ensure database name, username, password are correct
- Test connection manually

#### Add MySQL Connector to Test Classpath:
1. Right-click project → **Properties**
2. Go to **Libraries**
3. Under **Test Libraries**, ensure MySQL Connector is added
4. If not, add it: **Add Library** → **MySQL JDBC Driver**

---

### Issue 2: All Tests Failing

**Symptoms:**
- Every test shows error
- "Tests passed: 0.00%"

**Solutions:**

#### Check JUnit is Properly Added:
1. Right-click project → **Properties** → **Libraries**
2. Ensure **JUnit** is in **Test Libraries**
3. If missing, add it: **Add Library** → **JUnit**

#### Clean and Rebuild:
1. Right-click project → **Clean and Build**
2. Wait for build to complete
3. Try running tests again

#### Check Test Output:
- Look at the **Test Results** window
- Click on failed test to see error details
- Check for compilation errors

---

### Issue 3: Model Tests Failing

**Symptoms:**
- Model tests (CategoryTest, CustomerTest, etc.) fail
- Assertion errors

**Solutions:**

#### Verify Model Classes Match Tests:
- Check method names match (e.g., `getSaleDate()` vs `getSalesDate()`)
- Check constructor parameter order
- Check data types (int vs double, etc.)

#### Run Individual Tests:
- Right-click on specific test file → **Test File**
- See which specific test method fails
- Check error message for details

---

### Issue 4: DAO Tests Failing

**Symptoms:**
- DAO tests fail
- Database-related errors

**Solutions:**

#### Database Must Be Available:
- Start MySQL server
- Ensure database `car_spare_parts_db` exists
- Ensure tables exist (categories, customers, parts, suppliers, sales, users)

#### Check Test Data:
- Some tests create test data
- Ensure test IDs don't conflict with existing data
- Tests clean up after themselves

#### Skip Database Tests (Temporary):
- Tests now check if database is available
- If not available, tests are skipped (not failed)
- Fix database connection to run full tests

---

### Issue 5: Compilation Errors

**Symptoms:**
- Red underlines in test files
- "Cannot resolve symbol" errors

**Solutions:**

#### Check Imports:
- Ensure `import org.junit.Test;` is present
- Ensure `import static org.junit.Assert.*;` is present
- Ensure model classes are imported correctly

#### Rebuild Project:
1. **Clean and Build** project
2. Check for compilation errors in **Output** window
3. Fix any errors before running tests

---

## Quick Fix Checklist

When tests fail, check these in order:

- [ ] **JUnit Library Added?**
  - Project Properties → Libraries → Test Libraries → JUnit

- [ ] **MySQL Connector in Test Classpath?**
  - Project Properties → Libraries → Test Libraries → MySQL JDBC Driver

- [ ] **Database Running?**
  - Check MySQL service is started
  - Test connection manually

- [ ] **Database Credentials Correct?**
  - Check `DatabaseConnectionManager.java`
  - Verify username, password, database name

- [ ] **Project Cleaned and Rebuilt?**
  - Right-click project → Clean and Build

- [ ] **Test Output Checked?**
  - Look at Test Results window
  - Read error messages carefully

---

## Running Tests Without Database

If database is not available, you can:

1. **Run Only Model Tests:**
   - Model tests don't need database
   - Right-click `test/model` → Test
   - These should pass

2. **Skip DAO Tests:**
   - DAO tests now check database availability
   - If database unavailable, tests are skipped (not failed)

---

## Test Execution Order

### Recommended Order:

1. **First: Run Model Tests**
   ```bash
   # These don't need database
   - CategoryTest
   - CustomerTest
   - PartTest
   - SupplierTest
   - SalesTest
   - UserTest
   ```

2. **Second: Run DAO Tests**
   ```bash
   # These need database
   - CategoryDaoTest
   - CustomerDaoTest
   - UserDaoTest
   ```

---

## Getting Detailed Error Information

### In NetBeans:

1. **Test Results Window:**
   - Shows summary of all tests
   - Click on failed test to see details

2. **Output Window:**
   - Shows compilation errors
   - Shows runtime exceptions

3. **Test Output Tab:**
   - Shows detailed stack traces
   - Shows assertion failures

---

## Common Error Messages

### "Cannot resolve symbol 'org.junit'"
**Fix:** Add JUnit library to project

### "Communications link failure"
**Fix:** Check database is running and credentials are correct

### "Table 'categories' doesn't exist"
**Fix:** Create database tables (run SQL scripts)

### "AssertionError: expected <X> but was <Y>"
**Fix:** Check test expectations match actual model behavior

### "NullPointerException"
**Fix:** Check if database connection is null or object is null

---

## Still Having Issues?

1. **Check Test Results Window** for specific error messages
2. **Read error stack traces** carefully
3. **Run tests one at a time** to isolate the problem
4. **Verify database connection** manually
5. **Check all dependencies** are added

---

**Remember:** Model tests should work without database. If they fail, it's likely a code mismatch issue, not a database issue.

