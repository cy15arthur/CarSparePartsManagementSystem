# Quick Start Guide - GitHub Setup

## Prerequisites
- Git installed on your system
- GitHub account created

## Quick Setup (5 Steps)

### 1. Install Git (if not installed)
Download from: https://git-scm.com/download/win

### 2. Configure Git (one-time setup)
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 3. Initialize Repository
Run the PowerShell script:
```powershell
.\setup-git.ps1
```

Or manually:
```bash
git init
git add .
git commit -m "Initial commit: Car Spare Parts Management System"
```

### 4. Create GitHub Repository
1. Go to https://github.com/new
2. Repository name: `CarSparePartsManagementSystem`
3. **Don't** initialize with README
4. Click "Create repository"

### 5. Connect and Push
```bash
git remote add origin https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git
git branch -M main
git push -u origin main
```

## Daily Workflow

```bash
# Check what changed
git status

# Add changes
git add .

# Commit
git commit -m "Description of changes"

# Push to GitHub
git push origin main
```

## NetBeans Integration

1. **Enable Git**: Tools → Options → Versioning → Git
2. **Initialize**: Right-click project → Git → Initialize Repository
3. **Commit**: Right-click project → Git → Commit
4. **Push**: Right-click project → Git → Remote → Push

## Need Help?

See `GITHUB_SETUP.md` for detailed instructions.

