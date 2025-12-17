# GitHub Version Control - Complete Explanation

## What is Version Control?

**Version Control** (also called Source Control) is like a **time machine for your code**. It allows you to:
- ✅ Save snapshots of your project at different points in time
- ✅ Track all changes made to your files
- ✅ Go back to previous versions if something breaks
- ✅ Work with others on the same project
- ✅ See who made what changes and when

Think of it like **Google Docs history** but for code files.

---

## What is Git?

**Git** is a **version control system** that runs on your computer. It tracks changes in your project files.

### Git vs GitHub

| Git | GitHub |
|-----|--------|
| Software installed on your computer | Website that hosts your Git repositories |
| Tracks changes locally | Stores your code online (cloud backup) |
| Free and open source | Free for public repositories |
| Works offline | Requires internet to upload/download |

**Analogy**: Git is like a **camera** (takes snapshots), GitHub is like **Google Photos** (stores your photos online).

---

## Why Use GitHub for This Project?

1. **Backup**: Your code is safe in the cloud
2. **History**: See all changes you've made
3. **Collaboration**: Work with others easily
4. **Portfolio**: Show your work to potential employers
5. **Requirements**: Often required for academic projects

---

## Step-by-Step Setup Guide

### STEP 1: Install Git on Your Computer

#### For Windows:

1. **Download Git**:
   - Go to: https://git-scm.com/download/win
   - Click "Download for Windows"
   - The download will start automatically

2. **Install Git**:
   - Run the downloaded `.exe` file
   - Click "Next" through the installation wizard
   - **Use default settings** (recommended)
   - Click "Install"
   - Wait for installation to complete
   - Click "Finish"

3. **Verify Installation**:
   - Open **PowerShell** (Windows key + X, then select PowerShell)
   - Type: `git --version`
   - Press Enter
   - You should see something like: `git version 2.42.0`
   - ✅ If you see a version number, Git is installed!

**If you see an error**: Restart your computer and try again.

---

### STEP 2: Configure Git (First Time Only)

Tell Git who you are (this is used for commit messages):

1. Open **PowerShell**
2. Run these commands (replace with your info):

```bash
git config --global user.name "Your Full Name"
git config --global user.email "your.email@example.com"
```

**Example**:
```bash
git config --global user.name "John Doe"
git config --global user.email "john.doe@email.com"
```

**Note**: Use the **same email** you'll use for GitHub.

---

### STEP 3: Create a GitHub Account

1. Go to: https://github.com
2. Click **"Sign up"** (top right)
3. Enter:
   - Username (e.g., `johndoe`)
   - Email address
   - Password
4. Verify your email (check your inbox)
5. Complete the setup

**✅ You now have a GitHub account!**

---

### STEP 4: Create a Repository on GitHub

A **repository** (or "repo") is like a **folder** for your project on GitHub.

1. **Log in** to GitHub
2. Click the **"+"** icon (top right)
3. Select **"New repository"**
4. Fill in:
   - **Repository name**: `CarSparePartsManagementSystem`
   - **Description**: "Car Spare Parts Management System - Java Desktop Application"
   - **Visibility**: Choose **Public** (free) or **Private** (if you have GitHub Pro)
   - **⚠️ IMPORTANT**: Do NOT check:
     - ❌ "Add a README file"
     - ❌ "Add .gitignore"
     - ❌ "Choose a license"
   - (We already have these files)
5. Click **"Create repository"**

**✅ Your repository is created!**

You'll see a page with instructions. **Don't close this page yet!**

---

### STEP 5: Initialize Git in Your Project

Now, connect your local project to Git:

1. **Open PowerShell**
2. **Navigate to your project folder**:
   ```bash
   cd D:\Documents\NetBeansProjects\CarSparePartsManagementSystem
   ```

3. **Initialize Git** (creates a hidden `.git` folder):
   ```bash
   git init
   ```
   You should see: `Initialized empty Git repository...`

4. **Check status** (see what files Git is tracking):
   ```bash
   git status
   ```
   You'll see a list of files that aren't tracked yet.

---

### STEP 6: Add Files to Git

Tell Git which files to track:

1. **Add all files**:
   ```bash
   git add .
   ```
   (The `.` means "all files in current directory")

2. **Verify files were added**:
   ```bash
   git status
   ```
   Files should now appear in **green** (staged for commit).

---

### STEP 7: Make Your First Commit

A **commit** is like saving a snapshot of your project:

```bash
git commit -m "Initial commit: Car Spare Parts Management System"
```

**What this does**:
- `commit`: Save a snapshot
- `-m`: Add a message
- The message describes what this commit contains

**✅ Your first commit is done!**

---

### STEP 8: Connect to GitHub

Link your local repository to GitHub:

1. **Copy your repository URL** from GitHub (the page you didn't close)
   - It looks like: `https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git`
   - Replace `YOUR_USERNAME` with your actual GitHub username

2. **Add GitHub as remote** (in PowerShell):
   ```bash
   git remote add origin https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git
   ```
   **Example**:
   ```bash
   git remote add origin https://github.com/johndoe/CarSparePartsManagementSystem.git
   ```

3. **Verify remote was added**:
   ```bash
   git remote -v
   ```
   You should see your repository URL listed.

4. **Rename branch to main** (GitHub uses "main" now):
   ```bash
   git branch -M main
   ```

---

### STEP 9: Push to GitHub

Upload your code to GitHub:

```bash
git push -u origin main
```

**What happens**:
- Git asks for your GitHub **username** → Enter it
- Git asks for your **password** → **⚠️ Important**: Use a **Personal Access Token**, not your password!

#### Create Personal Access Token:

1. Go to GitHub → Click your profile (top right) → **Settings**
2. Scroll down → **Developer settings** (left sidebar)
3. Click **Personal access tokens** → **Tokens (classic)**
4. Click **Generate new token** → **Generate new token (classic)**
5. Fill in:
   - **Note**: "Car Spare Parts Project"
   - **Expiration**: Choose duration (90 days recommended)
   - **Scopes**: Check **`repo`** (full control of private repositories)
6. Click **Generate token**
7. **⚠️ COPY THE TOKEN IMMEDIATELY** (you won't see it again!)
8. Use this token as your password when Git asks

**After entering credentials**, your code will upload to GitHub!

**✅ Your code is now on GitHub!**

---

## Daily Workflow (How to Use Git Daily)

### Making Changes and Saving to GitHub:

```bash
# 1. Check what files changed
git status

# 2. Add changed files
git add .

# 3. Commit with a message
git commit -m "Description of what you changed"

# 4. Push to GitHub
git push origin main
```

**Example**:
```bash
git status
git add .
git commit -m "Add professional styling to PartManagement view"
git push origin main
```

---

## Common Git Commands Cheat Sheet

| Command | What It Does |
|---------|--------------|
| `git status` | See which files changed |
| `git add .` | Stage all files for commit |
| `git add filename.java` | Stage specific file |
| `git commit -m "message"` | Save snapshot with message |
| `git push` | Upload to GitHub |
| `git pull` | Download from GitHub |
| `git log` | See commit history |
| `git diff` | See what changed |

---

## Using Git in NetBeans (Easier Way)

NetBeans has built-in Git support:

### Enable Git in NetBeans:

1. Open NetBeans
2. Go to **Tools** → **Options** → **Versioning** → **Git**
3. Set **Git Executable Path**: Usually `C:\Program Files\Git\bin\git.exe`
4. Click **OK**

### Commit and Push in NetBeans:

1. **Right-click** on your project in Projects window
2. Select **Git** → **Commit...**
3. **Select files** to commit (check boxes)
4. **Enter commit message** (bottom)
5. Click **Commit**
6. To push: **Git** → **Remote** → **Push...**
7. Enter your GitHub credentials
8. Click **Next** → **Finish**

**✅ Much easier than command line!**

---

## Understanding .gitignore

The `.gitignore` file tells Git which files to **ignore** (not track).

**Why?** Some files shouldn't be in version control:
- Compiled files (`.class`)
- Build folders (`build/`, `dist/`)
- IDE settings (NetBeans private files)
- Log files

**✅ Your project already has a `.gitignore` file!**

It excludes:
- `*.class` files
- `build/` and `dist/` folders
- `nbproject/private/` (NetBeans private settings)
- Log files

---

## Troubleshooting

### Problem: "git is not recognized"
**Solution**: Git is not installed or not in PATH
- Install Git (Step 1)
- Restart PowerShell/terminal
- Restart computer if needed

### Problem: "Authentication failed"
**Solution**: Use Personal Access Token instead of password
- Create token (see Step 9)
- Use token as password

### Problem: "Repository not found"
**Solution**: Check repository URL
- Verify username in URL
- Make sure repository exists on GitHub
- Check if repository is private (need access)

### Problem: "Everything up-to-date" but files changed
**Solution**: You forgot to commit
```bash
git add .
git commit -m "Your message"
git push
```

---

## Best Practices

### 1. Commit Often
- Make small, frequent commits
- Don't wait until everything is done

### 2. Write Good Commit Messages
**Good**:
```
✅ "Add professional styling to PartManagement view"
✅ "Fix SQL syntax error in SupplierDaoImpl"
✅ "Add authentication system with login page"
```

**Bad**:
```
❌ "Update"
❌ "Fixed bug"
❌ "Changes"
```

### 3. Pull Before Push
Always pull latest changes before pushing:
```bash
git pull origin main
git push origin main
```

### 4. Don't Commit Sensitive Data
- ❌ Database passwords
- ❌ API keys
- ❌ Personal information

---

## Visual Summary

```
Your Computer          GitHub (Cloud)
┌─────────────┐       ┌─────────────┐
│             │       │             │
│  Project    │       │  Repository │
│  Files      │       │  (Backup)   │
│             │       │             │
│  [Git]      │◄─────►│  [GitHub]   │
│  (Local)    │ Push/ │  (Online)   │
│             │ Pull  │             │
└─────────────┘       └─────────────┘
```

**Workflow**:
1. Make changes in your code
2. `git add .` (stage changes)
3. `git commit -m "message"` (save snapshot)
4. `git push` (upload to GitHub)

---

## Quick Start Checklist

- [ ] Install Git
- [ ] Configure Git (name & email)
- [ ] Create GitHub account
- [ ] Create repository on GitHub
- [ ] Initialize Git in project (`git init`)
- [ ] Add files (`git add .`)
- [ ] Make first commit (`git commit -m "Initial commit"`)
- [ ] Connect to GitHub (`git remote add origin ...`)
- [ ] Push to GitHub (`git push -u origin main`)
- [ ] ✅ Done!

---

## Need Help?

- **Git Documentation**: https://git-scm.com/doc
- **GitHub Guides**: https://guides.github.com
- **Git Tutorial**: https://learngitbranching.js.org (interactive)

---

**Remember**: Version control is like saving your game progress. The more you save (commit), the safer your work is!

