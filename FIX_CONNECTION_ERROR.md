# Fix: "Connection was reset" Error

## Quick Fixes (Try These First)

### Solution 1: Use Personal Access Token (Most Common Fix)

GitHub no longer accepts passwords for HTTPS. You need a **Personal Access Token**.

#### Step 1: Create Token
1. Go to: https://github.com/settings/tokens
2. Click **"Generate new token"** → **"Generate new token (classic)"**
3. Fill in:
   - **Note**: "Car Spare Parts Project"
   - **Expiration**: 90 days (or your choice)
   - **Scopes**: Check **`repo`** (full control)
4. Click **"Generate token"**
5. **⚠️ COPY THE TOKEN IMMEDIATELY** (you won't see it again!)

#### Step 2: Use Token
When Git asks for password, **paste the token** (not your GitHub password).

---

### Solution 2: Increase Buffer Size

This often fixes connection issues:

**In Git Bash or PowerShell:**
```bash
git config --global http.postBuffer 524288000
git config --global http.maxRequestBuffer 100M
```

**Then try pushing again:**
```bash
git push origin main
```

---

### Solution 3: Disable SSL Verification (Temporary)

**⚠️ Only if other solutions don't work:**

```bash
git config --global http.sslVerify false
```

**Try pushing:**
```bash
git push origin main
```

**⚠️ Re-enable after:**
```bash
git config --global http.sslVerify true
```

---

### Solution 4: Clear Stored Credentials

**Windows Credential Manager:**
1. Press **Windows Key + R**
2. Type: `control /name Microsoft.CredentialManager`
3. Press Enter
4. Go to **"Windows Credentials"**
5. Find: `git:https://github.com`
6. Click it → **Remove**
7. Try pushing again (Git will ask for new credentials)

---

### Solution 5: Use SSH Instead

SSH is more reliable than HTTPS:

#### Step 1: Generate SSH Key
```bash
ssh-keygen -t ed25519 -C "your.email@example.com"
```
Press Enter 3 times (use defaults, no passphrase needed)

#### Step 2: Copy Public Key
```bash
cat ~/.ssh/id_ed25519.pub
```
Copy the entire output

#### Step 3: Add to GitHub
1. Go to: https://github.com/settings/keys
2. Click **"New SSH key"**
3. Paste your key → **"Add SSH key"**

#### Step 4: Change Remote URL
```bash
git remote set-url origin git@github.com:cy15arthur/CarSparePartsManagementSystem.git
```

#### Step 5: Test Connection
```bash
ssh -T git@github.com
```
Type "yes" if asked

#### Step 6: Push
```bash
git push -u origin main
```

---

## Using NetBeans (Easier)

If command line keeps failing, use NetBeans:

1. **Right-click** project → **Git** → **Remote** → **Push...**
2. Enter:
   - **Username**: `cy15arthur`
   - **Password**: Your Personal Access Token (not password!)
3. Click **Next** → **Finish**

NetBeans often handles connections better.

---

## Most Likely Solution

**90% of the time, it's one of these:**

1. ✅ **Use Personal Access Token** (not password)
2. ✅ **Increase buffer size**
3. ✅ **Clear old credentials**

---

## Step-by-Step: Fix Right Now

**Try this sequence:**

```bash
# 1. Increase buffer
git config --global http.postBuffer 524288000

# 2. Clear old credentials (Windows)
# Open Credential Manager and remove git:https://github.com

# 3. Try pushing again
git push origin main
# When asked for password, use Personal Access Token
```

---

## Still Not Working?

### Check Repository URL:
```bash
git remote -v
```

**Should show:**
```
origin  https://github.com/cy15arthur/CarSparePartsManagementSystem.git (fetch)
origin  https://github.com/cy15arthur/CarSparePartsManagementSystem.git (push)
```

**If wrong:**
```bash
git remote set-url origin https://github.com/cy15arthur/CarSparePartsManagementSystem.git
```

---

## Summary

**Most Common Fix:**
1. Create Personal Access Token on GitHub
2. Clear old credentials in Windows Credential Manager
3. Try pushing again, use token as password

**Alternative:**
- Use SSH instead of HTTPS
- Use NetBeans Git interface

**Quick Commands:**
```bash
git config --global http.postBuffer 524288000
git push origin main
# Use Personal Access Token when asked for password
```

