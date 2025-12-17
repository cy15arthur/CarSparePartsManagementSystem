# Fix: Hamcrest Core Missing Error

## Problem
Error: `java.lang.NoClassDefFoundError: org/hamcrest/SelfDescribing`

**Cause:** JUnit 4 requires **Hamcrest Core** library, but it's not in your test classpath.

---

## Solution: Add Hamcrest Core Library

### Step 1: Download Hamcrest Core

**Download Hamcrest Core 1.3:**
- Direct link: https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
- Or search: "hamcrest-core-1.3.jar download"

**Save the file** to a location you can find (e.g., `C:\Users\YourName\Downloads\`)

---

### Step 2: Add to NetBeans Project

1. **Right-click** your project → **Properties**

2. Go to **Libraries** → **Test Libraries** section

3. Click **Add JAR/Folder...**

4. Navigate to where you saved `hamcrest-core-1.3.jar`

5. Select the file → Click **Open**

6. Click **OK**

---

### Step 3: Verify Both Libraries Are Present

In **Properties** → **Libraries** → **Test Libraries**, you should see:
- ✅ `junit-4.x.x.jar` (or similar)
- ✅ `hamcrest-core-1.3.jar` (or similar)

**Both are required!**

---

### Step 4: Clean and Rebuild

1. Right-click project → **Clean and Build**
2. Wait for build to complete

---

### Step 5: Run Tests Again

1. Right-click `CategoryTest.java` → **Test File**
2. Tests should now run successfully!

---

## Alternative: Download Both Libraries

If you need to download both:

### JUnit 4.13.2:
- https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar

### Hamcrest Core 1.3:
- https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

**Add both to Test Libraries** following Step 2 above.

---

## Quick Checklist

- [ ] Hamcrest Core JAR downloaded
- [ ] Added to **Test Libraries** (not regular Libraries)
- [ ] Both JUnit and Hamcrest Core are in Test Libraries
- [ ] Project cleaned and rebuilt
- [ ] Tests run successfully

---

## Why This Happens

JUnit 4 uses Hamcrest for assertions and matchers. When you add JUnit, NetBeans sometimes doesn't automatically add Hamcrest Core as a dependency, so you need to add it manually.

---

**Once Hamcrest Core is added, the `NoClassDefFoundError` should be resolved!**

