# How to Execute SQL File in MySQL

## You're Already in MySQL - Here's How to Execute the File

### Method 1: Source Command (Easiest)

Since you're already in MySQL prompt (`mysql>`), use:

```sql
source setup_database.sql
```

Or with full path:
```sql
source D:/Documents/NetBeansProjects/CarSparePartsManagementSystem/setup_database.sql
```

**Note:** Use forward slashes `/` or double backslashes `\\` in the path.

---

### Method 2: Copy and Paste

1. Open `setup_database.sql` in a text editor
2. Copy all contents
3. Paste into MySQL command line
4. Press Enter

---

### Method 3: Exit MySQL and Use Command Line

If you want to exit MySQL first:

```bash
exit
```

Then run:
```bash
mysql -u cyitatire -p car_spare_parts_db < setup_database.sql
```

---

## Important: You Already Have Tables!

You have existing tables. The setup script will **DROP** existing tables. 

**Options:**

### Option A: Keep Existing Tables (Recommended)
Check if your existing tables have the correct structure. If they do, you don't need to run the script!

### Option B: Backup and Recreate
If you want to use the new script:
1. **Backup your data first:**
   ```sql
   mysqldump -u cyitatire -p car_spare_parts_db > backup.sql
   ```
2. Then run the setup script

### Option C: Modify Script to Not Drop Tables
Remove the `DROP TABLE` lines from the script.

---

## Check Your Existing Tables Structure

Run these to see if your tables match what's needed:

```sql
DESCRIBE customers;
DESCRIBE categories;
DESCRIBE suppliers;
DESCRIBE parts;
DESCRIBE sales;
```

Compare with what the application expects (see `CustomerDaoImpl.java` for column names).

---

## Quick Check: Does Your Customers Table Work?

Test if your existing `customers` table has the right structure:

```sql
DESCRIBE customers;
```

**Should have these columns:**
- customer_id
- customer_first_name
- customer_last_name
- customer_email
- phone

If it matches, your tables are fine! Just run the tests again.

---

## If Tables Don't Match

If your table structure is different, you can:

1. **Alter existing tables** to match
2. **Or run the setup script** (will drop and recreate)

---

**Since you're already in MySQL, just type: `source setup_database.sql`**

