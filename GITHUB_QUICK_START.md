# GitHub Quick Start - 5 Minutes

## Prerequisites Check

✅ **Do you have Git installed?**
- Open PowerShell
- Type: `git --version`
- ✅ If you see a version → Skip to Step 3
- ❌ If error → Do Step 1 & 2

---

## Step 1: Install Git (2 minutes)

1. Download: https://git-scm.com/download/win
2. Run installer → Click "Next" → Click "Install"
3. Restart PowerShell
4. Verify: `git --version`

---

## Step 2: Configure Git (30 seconds)

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

---

## Step 3: Create GitHub Account (1 minute)

1. Go to: https://github.com
2. Sign up (free)
3. Verify email

---

## Step 4: Create Repository (1 minute)

1. GitHub → Click "+" → "New repository"
2. Name: `CarSparePartsManagementSystem`
3. **Don't check** README, .gitignore, or license
4. Click "Create repository"
5. **Copy the repository URL** (you'll need it)

---

## Step 5: Connect and Push (1 minute)

Open PowerShell in your project folder:

```bash
# Navigate to project
cd D:\Documents\NetBeansProjects\CarSparePartsManagementSystem

# Initialize Git
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit: Car Spare Parts Management System"

# Connect to GitHub (replace YOUR_USERNAME)
git remote add origin https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git

# Push to GitHub
git branch -M main
git push -u origin main
```

**When asked for password**: Use a **Personal Access Token** (not your GitHub password)

### Create Token:
1. GitHub → Profile → Settings → Developer settings
2. Personal access tokens → Tokens (classic)
3. Generate new token → Check `repo` → Generate
4. **Copy token** → Use as password

---

## ✅ Done!

Your code is now on GitHub!

**View it**: Go to `https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem`

---

## Daily Use (After Setup)

```bash
# Make changes to your code, then:

git add .
git commit -m "Description of changes"
git push
```

That's it! 🎉

