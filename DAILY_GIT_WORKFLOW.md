# Daily Git Workflow - Practical Guide

## Scenario: You've Created/Updated SupplierManagement View

This guide shows you exactly what to do when you make changes to your project.

---

## Step-by-Step: Adding Your New SupplierManagement View

### Step 1: Check What Changed

First, see what files Git detected as changed:

```bash
git status
```

**What you'll see:**
```
On branch main
Changes not staged for commit:
  modified:   src/view/SupplierManagement.java
  modified:   src/view/SupplierManagement.form (if exists)
  modified:   src/util/UITheme.java (if you updated it)
  
Untracked files:
  src/view/SupplierManagement.java (if it's new)
```

**This tells you:**
- ✅ Which files were modified
- ✅ Which files are new (untracked)

---

### Step 2: Review Your Changes (Optional but Recommended)

See exactly what changed in a file:

```bash
git diff src/view/SupplierManagement.java
```

**What you'll see:**
- Lines in **red** with `-` = deleted
- Lines in **green** with `+` = added
- This helps you verify your changes before committing

**To see all changes:**
```bash
git diff
```

---

### Step 3: Stage Your Changes

Tell Git which files you want to include in this commit:

#### Option A: Add All Changed Files
```bash
git add .
```
(This adds ALL modified and new files)

#### Option B: Add Specific Files Only
```bash
git add src/view/SupplierManagement.java
git add src/util/UITheme.java
```

**For your SupplierManagement example:**
```bash
# Add the view file
git add src/view/SupplierManagement.java

# If you also updated UITheme
git add src/util/UITheme.java

# If you created a form file (though we deleted these)
git add src/view/SupplierManagement.form
```

**Verify what's staged:**
```bash
git status
```

Files should now appear in **green** (staged for commit).

---

### Step 4: Commit Your Changes

Save a snapshot with a descriptive message:

```bash
git commit -m "Add professional SupplierManagement view with modern UI styling"
```

**Good commit message examples:**
```
✅ "Add professional SupplierManagement view with modern UI styling"
✅ "Update SupplierManagement: Add professional styling using UITheme"
✅ "feat: Add SupplierManagement view with card-based layout"
✅ "style: Apply modern UI theme to SupplierManagement view"
```

**Bad commit messages (avoid these):**
```
❌ "Update"
❌ "Changes"
❌ "Fixed"
❌ "Supplier"
```

**Why?** Good messages help you (and others) understand what changed later!

---

### Step 5: Push to GitHub

Upload your changes to GitHub:

```bash
git push origin main
```

**What happens:**
- Your changes are uploaded to GitHub
- Others can see your updates
- Your code is backed up in the cloud

**✅ Done!** Your SupplierManagement view is now on GitHub.

---

## Complete Example: Full Workflow

Here's the complete sequence for your SupplierManagement view:

```bash
# 1. Check what changed
git status

# 2. (Optional) Review changes
git diff src/view/SupplierManagement.java

# 3. Stage the file
git add src/view/SupplierManagement.java

# 4. Commit with message
git commit -m "Add professional SupplierManagement view with modern UI styling"

# 5. Push to GitHub
git push origin main
```

**That's it!** 🎉

---

## Common Daily Scenarios

### Scenario 1: You Modified Multiple Files

**Example:** You updated SupplierManagement AND fixed a bug in SupplierDaoImpl

```bash
# Check status
git status

# Add all changes
git add .

# Commit with descriptive message
git commit -m "Update SupplierManagement UI and fix bug in SupplierDaoImpl"

# Push
git push origin main
```

---

### Scenario 2: You Want to Commit Files Separately

**Example:** You updated SupplierManagement AND added a new utility function

```bash
# Commit SupplierManagement first
git add src/view/SupplierManagement.java
git commit -m "Add professional SupplierManagement view"

# Then commit the utility separately
git add src/util/SomeUtility.java
git commit -m "Add utility function for supplier validation"

# Push both commits
git push origin main
```

---

### Scenario 3: You Made a Mistake

**Example:** You committed with wrong message or forgot a file

#### Fix commit message (before pushing):
```bash
git commit --amend -m "Correct message here"
```

#### Add forgotten file to last commit:
```bash
git add forgotten-file.java
git commit --amend --no-edit
```

---

## Best Practices for Daily Work

### 1. Commit Often, Push Regularly

**Good:**
- Make small commits throughout the day
- Push at least once per day
- Commit when a feature is complete

**Bad:**
- Waiting weeks to commit
- Making huge commits with 50+ files
- Never pushing to GitHub

### 2. Write Descriptive Commit Messages

**Format:**
```
[Type]: [What you did] - [Why/Details]

Examples:
feat: Add SupplierManagement view with modern UI
fix: Fix SQL error in SupplierDaoImpl update method
style: Apply UITheme to SupplierManagement components
refactor: Improve error handling in SupplierDaoImpl
docs: Add JavaDoc to SupplierManagement class
```

**Common Types:**
- `feat`: New feature
- `fix`: Bug fix
- `style`: Code formatting/styling
- `refactor`: Code restructuring
- `docs`: Documentation
- `test`: Adding tests

### 3. Review Before Committing

Always check what you're committing:
```bash
git status        # See what's changed
git diff          # See exact changes
```

### 4. Pull Before Push (When Working with Others)

If others are working on the project:
```bash
git pull origin main    # Get latest changes
git push origin main    # Push your changes
```

---

## Using NetBeans for Git (Easier Way)

### Visual Git in NetBeans:

1. **See Changes:**
   - Right-click project → **Git** → **Show Changes**
   - Or use **Team** → **Git** → **Show Changes**

2. **Commit:**
   - Right-click project → **Git** → **Commit...**
   - Select files to commit (check boxes)
   - Enter commit message
   - Click **Commit**

3. **Push:**
   - Right-click project → **Git** → **Remote** → **Push...**
   - Enter credentials
   - Click **Next** → **Finish**

**Much easier than command line!**

---

## Real-World Example: Your SupplierManagement View

Let's say you just finished updating SupplierManagement with professional styling:

```bash
# 1. Check what you changed
git status

# Output:
# modified:   src/view/SupplierManagement.java
# modified:   src/util/UITheme.java

# 2. Review the changes (optional)
git diff src/view/SupplierManagement.java

# 3. Stage both files
git add src/view/SupplierManagement.java
git add src/util/UITheme.java

# Or add all at once:
git add .

# 4. Commit with good message
git commit -m "style: Apply modern UI theme to SupplierManagement view

- Updated SupplierManagement with UITheme colors and fonts
- Added professional button styling
- Improved table appearance
- Enhanced form layout"

# 5. Push to GitHub
git push origin main
```

**Result:** Your changes are now saved and backed up on GitHub! ✅

---

## Quick Reference Card

### Daily Workflow (Copy This!)

```bash
# 1. Check status
git status

# 2. Add files
git add .                    # All files
# OR
git add filename.java        # Specific file

# 3. Commit
git commit -m "Your message here"

# 4. Push
git push origin main
```

### Useful Commands

```bash
git status              # See what changed
git diff                # See exact changes
git log                 # See commit history
git log --oneline       # Compact history
git pull                # Get latest from GitHub
git push                # Upload to GitHub
```

---

## Troubleshooting Daily Issues

### Problem: "Your branch is behind"
**Solution:**
```bash
git pull origin main
# Resolve any conflicts if needed
git push origin main
```

### Problem: "Nothing to commit"
**Solution:** You haven't made any changes, or you forgot to save files

### Problem: "Changes not staged"
**Solution:** You need to `git add` before `git commit`

### Problem: "Failed to push"
**Solution:** 
- Check internet connection
- Make sure you're authenticated
- Try: `git pull` first, then `git push`

---

## Summary

**Daily Git Workflow:**
1. ✅ Make changes to your code
2. ✅ `git status` - Check what changed
3. ✅ `git add .` - Stage changes
4. ✅ `git commit -m "message"` - Save snapshot
5. ✅ `git push` - Upload to GitHub

**For your SupplierManagement view:**
- Follow the same steps
- Use descriptive commit message
- Push regularly

**Remember:** Commit often, push regularly, write good messages! 🚀

