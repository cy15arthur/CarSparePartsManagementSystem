# JUnit Setup Guide

## Adding JUnit to NetBeans Project

### Step 1: Add JUnit Library

1. **Right-click** on your project in Projects window
2. Select **Properties**
3. Go to **Libraries** category
4. Click **Add Library...** button
5. Select **JUnit** from the list
6. Click **Add Library**
7. Click **OK**

### Step 2: Verify JUnit is Added

- Check **Libraries** node in Projects window
- You should see `junit-4.x.x.jar` and `hamcrest-core-x.x.jar`

### Step 3: Run Tests

1. Right-click on any test file (e.g., `CategoryTest.java`)
2. Select **Test File** or **Run File**
3. Tests should execute

---

## Alternative: Download JUnit Manually

If JUnit is not available in NetBeans:

1. **Download JUnit 4.13.2**:
   - https://github.com/junit-team/junit4/releases
   - Download `junit-4.13.2.jar`

2. **Download Hamcrest Core**:
   - https://github.com/hamcrest/JavaHamcrest/releases
   - Download `hamcrest-core-1.3.jar`

3. **Add to Project**:
   - Right-click project → **Properties** → **Libraries**
   - Click **Add JAR/Folder...**
   - Select downloaded JAR files
   - Click **OK**

---

## Verify Setup

Create a simple test to verify:

```java
package test;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleTest {
    @Test
    public void testSimple() {
        assertTrue(true);
    }
}
```

If this test runs successfully, JUnit is set up correctly!

---

## Troubleshooting

### Problem: "Cannot resolve symbol 'org.junit'"
**Solution**: JUnit library not added. Follow Step 1 above.

### Problem: Tests don't run
**Solution**: 
- Check JUnit is in Libraries
- Clean and rebuild project
- Restart NetBeans

### Problem: "No tests found"
**Solution**:
- Ensure test class name ends with `Test`
- Ensure test methods are annotated with `@Test`
- Check test is in `test/` directory

---

**Once JUnit is added, you can run all the test files!**

