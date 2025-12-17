# Example: Committing SupplierManagement View

## Real Example - Step by Step

This shows exactly what to do when you've updated SupplierManagement view.

---

## Scenario

You just finished:
- ✅ Updating SupplierManagement.java with professional styling
- ✅ Applied UITheme colors and fonts
- ✅ Made buttons look modern
- ✅ Improved table design

Now you want to save this to Git and push to GitHub.

---

## Step 1: Check Status

```bash
git status
```

**Expected Output:**
```
On branch main
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   src/view/SupplierManagement.java

no changes added to commit (use "git add" to stage)
```

**What this means:**
- ✅ SupplierManagement.java was modified
- ⚠️ Changes are not staged yet (not ready to commit)

---

## Step 2: Review Changes (Optional)

See exactly what you changed:

```bash
git diff src/view/SupplierManagement.java
```

**You'll see something like:**
```diff
-        jPanel1.setBackground(new java.awt.Color(220, 38, 38));
+        jPanel1.setBackground(UITheme.SURFACE);
+        jPanel1.setBorder(UITheme.createCardBorder());

-        saveBtn.setForeground(new java.awt.Color(241, 19, 6));
+        saveBtn.setBackground(UITheme.BUTTON_SECONDARY);
+        saveBtn.setFont(UITheme.FONT_BUTTON);
+        saveBtn.setForeground(UITheme.TEXT_WHITE);
```

**This confirms:** You're committing the right changes.

---

## Step 3: Stage the File

Add the file to staging area:

```bash
git add src/view/SupplierManagement.java
```

**Or add all modified files:**
```bash
git add .
```

**Check status again:**
```bash
git status
```

**Expected Output:**
```
On branch main
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   src/view/SupplierManagement.java
```

**What this means:**
- ✅ File is now staged (ready to commit)
- ✅ It appears in green (staged)

---

## Step 4: Commit

Save the snapshot with a descriptive message:

```bash
git commit -m "style: Apply modern UI theme to SupplierManagement view"
```

**Better message (more details):**
```bash
git commit -m "style: Apply modern UI theme to SupplierManagement view

- Updated background colors using UITheme
- Applied professional button styling
- Improved form layout and spacing
- Enhanced table appearance with better colors"
```

**Expected Output:**
```
[main abc1234] style: Apply modern UI theme to SupplierManagement view
 1 file changed, 45 insertions(+), 38 deletions(-)
```

**What this means:**
- ✅ Commit created successfully
- ✅ 45 lines added, 38 lines removed
- ✅ Commit ID: abc1234

---

## Step 5: Push to GitHub

Upload to GitHub:

```bash
git push origin main
```

**Expected Output:**
```
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Delta compression using up to 8 threads
Compressing objects: 100% (3/3), done.
Writing objects: 100% (3/3), 2.5 KiB | 2.5 MiB/s, done.
Total 3 (delta 1), reused 0 (delta 0)
To https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git
   def5678..abc1234  main -> main
```

**What this means:**
- ✅ Changes uploaded successfully
- ✅ Your code is now on GitHub
- ✅ Others can see your updates

---

## Complete Command Sequence

Here's the complete sequence in one go:

```bash
# 1. Check what changed
git status

# 2. (Optional) Review changes
git diff src/view/SupplierManagement.java

# 3. Stage the file
git add src/view/SupplierManagement.java

# 4. Commit
git commit -m "style: Apply modern UI theme to SupplierManagement view"

# 5. Push
git push origin main
```

**Time:** ~30 seconds ⚡

---

## If You Modified Multiple Files

**Example:** You updated SupplierManagement AND UITheme:

```bash
# Check status
git status

# Stage both files
git add src/view/SupplierManagement.java
git add src/util/UITheme.java

# Or stage all at once
git add .

# Commit
git commit -m "style: Update SupplierManagement and enhance UITheme

- Applied modern styling to SupplierManagement view
- Added new color constants to UITheme
- Improved button border styling"

# Push
git push origin main
```

---

## Using NetBeans (Visual Way)

### Option 1: NetBeans Git Menu

1. **Right-click** on `SupplierManagement.java` in Projects window
2. Select **Git** → **Commit...**
3. **Check the box** next to `SupplierManagement.java`
4. **Enter commit message**: "style: Apply modern UI theme to SupplierManagement view"
5. Click **Commit**
6. To push: **Git** → **Remote** → **Push...**
7. Enter credentials → **Next** → **Finish**

### Option 2: NetBeans Team Menu

1. **Right-click** project → **Team** → **Git** → **Commit...**
2. Follow same steps as above

**Much easier than typing commands!** 🎨

---

## Commit Message Examples

### Good Messages:

```
✅ "style: Apply modern UI theme to SupplierManagement view"
✅ "feat: Add professional SupplierManagement interface"
✅ "refactor: Update SupplierManagement with UITheme styling"
✅ "style: Modernize SupplierManagement UI components"
```

### Bad Messages (Avoid):

```
❌ "Update"
❌ "Changes"
❌ "Supplier"
❌ "Fixed"
❌ "asdf"
```

---

## What Happens After Push?

1. ✅ Your changes are on GitHub
2. ✅ You can see them at: `https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem`
3. ✅ Others can pull your changes
4. ✅ Your code is backed up
5. ✅ You can view commit history

**View on GitHub:**
- Go to your repository
- Click on `SupplierManagement.java`
- Click **History** to see all commits
- Click on a commit to see what changed

---

## Quick Checklist

When you update SupplierManagement (or any file):

- [ ] Made changes to code
- [ ] Saved files in NetBeans
- [ ] `git status` - Check what changed
- [ ] `git add .` - Stage changes
- [ ] `git commit -m "message"` - Commit
- [ ] `git push` - Push to GitHub
- [ ] ✅ Done!

---

## Summary

**For SupplierManagement view (or any change):**

1. **Check**: `git status`
2. **Stage**: `git add .`
3. **Commit**: `git commit -m "descriptive message"`
4. **Push**: `git push origin main`

**That's your daily workflow!** 🚀

