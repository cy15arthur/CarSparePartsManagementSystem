# Fix: JUnit Not Found Error

## Problem
All tests are failing because JUnit library is not properly added to the **Test Libraries**.

Error: `The import org.junit cannot be resolved`

## Solution: Add JUnit to Test Libraries

### Step-by-Step Fix:

1. **Right-click** on your project in the Projects window
   - Project name: `CarSparePartsManagementSystem`

2. Select **Properties**

3. In the Properties window:
   - Click on **Libraries** in the left panel
   - Look for **Test Libraries** section (at the bottom)

4. **Add JUnit Library:**
   - Click **Add Library...** button (in the Test Libraries section)
   - In the dialog, select **JUnit**
   - Click **Add Library**
   - Click **OK**

5. **Verify JUnit is Added:**
   - In the Libraries list, you should see:
     - `junit-4.x.x.jar` (or similar)
     - `hamcrest-core-x.x.jar` (or similar)
   - These should be under **Test Libraries** section

6. **Clean and Rebuild:**
   - Right-click project → **Clean and Build**
   - Wait for build to complete

7. **Run Tests Again:**
   - Right-click `CategoryTest.java` → **Test File**
   - Tests should now compile and run

---

## Alternative: Manual JUnit Setup

If JUnit is not available in the Add Library dialog:

### Option 1: Download JUnit Manually

1. **Download JUnit 4.13.2:**
   - Go to: https://github.com/junit-team/junit4/releases
   - Download `junit-4.13.2.jar`

2. **Download Hamcrest Core:**
   - Go to: https://github.com/hamcrest/JavaHamcrest/releases
   - Download `hamcrest-core-1.3.jar`

3. **Add to Project:**
   - Right-click project → **Properties** → **Libraries**
   - Under **Test Libraries**, click **Add JAR/Folder...**
   - Navigate to downloaded JAR files
   - Select both JAR files
   - Click **Open**
   - Click **OK**

### Option 2: Use Maven/Gradle (if project uses it)

If your project uses Maven, add to `pom.xml`:
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

---

## Verify Fix

After adding JUnit:

1. **Check for Red Underlines:**
   - Open `CategoryTest.java`
   - Red underlines should disappear
   - No "cannot resolve" errors

2. **Run a Simple Test:**
   - Right-click `CategoryTest.java` → **Test File**
   - Should compile and run (even if some tests fail)

3. **Check Test Results:**
   - Test Results window should show tests running
   - No compilation errors

---

## Common Issues

### Issue: "Add Library" doesn't show JUnit
**Solution:** 
- NetBeans might not have JUnit in default libraries
- Use manual download method (Option 1 above)

### Issue: JUnit added but still errors
**Solution:**
1. Clean and rebuild project
2. Close and reopen NetBeans
3. Check JUnit is in **Test Libraries** (not regular Libraries)

### Issue: Tests compile but fail
**Solution:**
- This is different - tests are running but assertions fail
- Check actual test failures in Test Results window
- This guide fixes compilation errors, not test logic errors

---

## Quick Checklist

- [ ] JUnit added to **Test Libraries** (not regular Libraries)
- [ ] Both `junit-x.x.x.jar` and `hamcrest-core-x.x.jar` are present
- [ ] Project cleaned and rebuilt
- [ ] No red underlines in test files
- [ ] Tests can be run (even if they fail)

---

**Once JUnit is properly added, all compilation errors should disappear and tests should run!**

