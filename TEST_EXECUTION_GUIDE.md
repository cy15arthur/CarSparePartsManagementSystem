# Test Execution Guide

## Quick Start

### Prerequisites
1. ✅ JUnit 4 library added to project
2. ✅ Test database configured
3. ✅ Database connection working

### Running Tests in NetBeans

#### Run All Tests:
1. Right-click on project → **Test**
2. Or press **Alt + F6**
3. View results in **Test Results** window

#### Run Single Test:
1. Right-click on test file (e.g., `CategoryTest.java`)
2. Select **Run File** or **Test File**
3. View results

#### Run Test Suite:
1. Create a test suite class
2. Right-click → **Run File**

---

## Running Tests from Command Line

### Using Ant (NetBeans):

```bash
# Navigate to project directory
cd D:\Documents\NetBeansProjects\CarSparePartsManagementSystem

# Run all tests
ant test

# Run single test
ant test-single -Dtest.includes=**/CategoryTest.java
```

### Using Java Directly:

```bash
# Compile tests
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:build/classes" test/model/*.java

# Run tests
java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:build/classes:test" org.junit.runner.JUnitCore model.CategoryTest
```

---

## Test Results Interpretation

### Success Indicators:
- ✅ Green checkmark = Test passed
- ✅ All assertions passed
- ✅ No exceptions thrown

### Failure Indicators:
- ❌ Red X = Test failed
- ❌ Assertion failed
- ❌ Exception thrown

### Common Issues:

#### 1. Database Connection Error
**Problem**: Tests fail with SQLException  
**Solution**: 
- Check database is running
- Verify connection credentials in `DatabaseConnectionManager`
- Ensure test database exists

#### 2. JUnit Not Found
**Problem**: "Cannot resolve symbol 'org.junit'"  
**Solution**:
- Add JUnit library to project
- In NetBeans: Right-click project → Properties → Libraries → Add Library → JUnit

#### 3. Test Data Issues
**Problem**: Tests fail due to missing data  
**Solution**:
- Set up test database with sample data
- Or use test data setup in `@Before` methods

---

## Test Coverage

### View Coverage in NetBeans:
1. Right-click project → **Test**
2. After tests run, right-click project → **Show Test Coverage**
3. Green = Covered, Red = Not covered

### Coverage Goals:
- Model Classes: 100%
- DAO Classes: 80%+
- Utility Classes: 90%+

---

## Test Data Management

### Before Running Tests:
1. Ensure test database is set up
2. Create required tables
3. Optionally insert test data

### After Running Tests:
1. Clean up test data (if needed)
2. Review test results
3. Fix any failures

---

## Continuous Testing

### Best Practices:
- ✅ Run tests frequently during development
- ✅ Run tests before committing code
- ✅ Fix failing tests immediately
- ✅ Maintain test coverage

---

## Troubleshooting

### Issue: Tests don't run
**Solution**: Check JUnit library is added

### Issue: Database errors
**Solution**: Verify database connection and credentials

### Issue: Tests pass locally but fail on CI
**Solution**: Check environment differences (database, paths, etc.)

---

## Test Reports

Test reports are generated in:
- `build/test/results/` - XML results
- `build/test/results/html/` - HTML reports

View HTML reports in browser for detailed results.

---

**For detailed test plan, see `TEST_PLAN.md`**

