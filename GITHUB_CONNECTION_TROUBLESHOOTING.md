# GitHub Connection Error - Troubleshooting Guide

## Error: "fatal: unable to access... Connection was reset"

This error means Git can't connect to GitHub. Here are solutions:

---

## Solution 1: Check Internet Connection

**Test if you can reach GitHub:**

```bash
ping github.com
```

**If ping fails:**
- Check your internet connection
- Try accessing https://github.com in your browser
- Check if you're behind a firewall

---

## Solution 2: Try Again (Temporary Issue)

Sometimes GitHub has temporary issues. Try again:

```bash
git push origin main
```

**Wait a few minutes and retry** - GitHub might be temporarily down.

---

## Solution 3: Check Proxy Settings

If you're behind a corporate firewall or proxy:

### Check if you have proxy:
```bash
git config --global http.proxy
git config --global https.proxy
```

### If you need to set proxy:
```bash
# Set HTTP proxy
git config --global http.proxy http://proxy.company.com:8080

# Set HTTPS proxy
git config --global https.proxy https://proxy.company.com:8080
```

### If you DON'T need proxy (remove it):
```bash
git config --global --unset http.proxy
git config --global --unset https.proxy
```

---

## Solution 4: Use SSH Instead of HTTPS

HTTPS might be blocked. Try using SSH:

### Step 1: Check if you have SSH key
```bash
ls ~/.ssh
```

### Step 2: Generate SSH key (if you don't have one)
```bash
ssh-keygen -t ed25519 -C "your.email@example.com"
```

Press Enter to accept default location and set a passphrase (optional).

### Step 3: Add SSH key to GitHub
1. Copy your public key:
   ```bash
   cat ~/.ssh/id_ed25519.pub
   ```
2. Copy the entire output
3. Go to GitHub → Settings → SSH and GPG keys
4. Click "New SSH key"
5. Paste your key → Save

### Step 4: Change remote URL to SSH
```bash
# Remove HTTPS remote
git remote remove origin

# Add SSH remote (replace YOUR_USERNAME)
git remote add origin git@github.com:YOUR_USERNAME/CarSparePartsManagementSystem.git

# Verify
git remote -v

# Try pushing again
git push -u origin main
```

---

## Solution 5: Increase Buffer Size

Sometimes large files cause connection issues:

```bash
git config --global http.postBuffer 524288000
git config --global http.maxRequestBuffer 100M
```

Then try again:
```bash
git push origin main
```

---

## Solution 6: Disable SSL Verification (Temporary - Not Recommended)

**⚠️ Only use if other solutions don't work:**

```bash
git config --global http.sslVerify false
```

**Try pushing:**
```bash
git push origin main
```

**⚠️ Re-enable SSL after:**
```bash
git config --global http.sslVerify true
```

---

## Solution 7: Check Authentication

### If using HTTPS with Personal Access Token:

1. **Make sure you're using a token, not password:**
   - GitHub no longer accepts passwords for HTTPS
   - You need a Personal Access Token

2. **Create/Update Token:**
   - GitHub → Settings → Developer settings
   - Personal access tokens → Tokens (classic)
   - Generate new token
   - Check `repo` scope
   - Copy token

3. **Use token as password when Git asks for credentials**

### Update stored credentials:

**Windows (Credential Manager):**
1. Open "Credential Manager" (search in Start menu)
2. Go to "Windows Credentials"
3. Find `git:https://github.com`
4. Remove or update it
5. Try pushing again (Git will ask for new credentials)

---

## Solution 8: Try Different Network

- Switch to mobile hotspot
- Try different WiFi network
- Check if VPN is interfering

---

## Solution 9: Verify Repository URL

Make sure the repository URL is correct:

```bash
git remote -v
```

**Should show:**
```
origin  https://github.com/cy15arthur/CarSparePartsManagementSystem.git (fetch)
origin  https://github.com/cy15arthur/CarSparePartsManagementSystem.git (push)
```

**If wrong, fix it:**
```bash
git remote set-url origin https://github.com/cy15arthur/CarSparePartsManagementSystem.git
```

---

## Solution 10: Check GitHub Status

GitHub might be down:
- Check: https://www.githubstatus.com/
- Check: https://twitter.com/githubstatus

---

## Quick Fix Checklist

Try these in order:

- [ ] Check internet connection (`ping github.com`)
- [ ] Try again (wait 1-2 minutes)
- [ ] Check if GitHub is down (githubstatus.com)
- [ ] Verify repository URL (`git remote -v`)
- [ ] Check proxy settings
- [ ] Try SSH instead of HTTPS
- [ ] Update credentials (use Personal Access Token)
- [ ] Increase buffer size
- [ ] Try different network

---

## Most Common Solutions

### For Most Users:

1. **Try again** (wait 1-2 minutes)
2. **Check internet** (ping github.com)
3. **Use Personal Access Token** (not password)
4. **Try SSH** (if HTTPS keeps failing)

---

## Still Not Working?

### Get More Information:

```bash
# Verbose output to see exact error
GIT_CURL_VERBOSE=1 GIT_TRACE=1 git push origin main
```

This will show detailed error information.

---

## Alternative: Use NetBeans Git

If command line keeps failing, try NetBeans:

1. Right-click project → **Git** → **Remote** → **Push...**
2. NetBeans might handle connection better
3. Enter credentials when prompted

---

## Summary

**Most likely causes:**
1. Network/firewall blocking HTTPS
2. Need to use Personal Access Token
3. GitHub temporarily unavailable
4. Proxy settings interfering

**Quick fixes:**
1. Try again in 1-2 minutes
2. Switch to SSH
3. Update credentials
4. Check internet connection

