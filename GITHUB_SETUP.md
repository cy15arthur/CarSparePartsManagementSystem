# GitHub Version Control Setup Guide

This guide will help you set up GitHub version control for the Car Spare Parts Management System project.

## Step 1: Install Git

### For Windows:
1. Download Git for Windows from: https://git-scm.com/download/win
2. Run the installer and follow the installation wizard
3. Use default settings (recommended)
4. After installation, restart your terminal/command prompt

### Verify Installation:
Open PowerShell or Command Prompt and run:
```bash
git --version
```
You should see the Git version number if installation was successful.

## Step 2: Configure Git (First Time Setup)

Set your Git username and email (use your GitHub credentials):

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

## Step 3: Create GitHub Account and Repository

1. Go to https://github.com and sign up (if you don't have an account)
2. Click the "+" icon in the top right corner
3. Select "New repository"
4. Repository name: `CarSparePartsManagementSystem`
5. Description: "Car Spare Parts Management System - Final Exam Project"
6. Choose **Public** or **Private** (as per your requirement)
7. **DO NOT** initialize with README, .gitignore, or license (we already have these)
8. Click "Create repository"

## Step 4: Initialize Git Repository (Local)

Open PowerShell/Command Prompt in your project directory and run:

```bash
# Navigate to project directory
cd D:\Documents\NetBeansProjects\CarSparePartsManagementSystem

# Initialize Git repository
git init

# Add all files to staging
git add .

# Make initial commit
git commit -m "Initial commit: Car Spare Parts Management System with best practices implementation"
```

## Step 5: Connect Local Repository to GitHub

After creating the repository on GitHub, you'll see instructions. Use these commands:

```bash
# Add remote repository (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git

# Verify remote was added
git remote -v

# Push to GitHub
git branch -M main
git push -u origin main
```

If you're using SSH instead of HTTPS:
```bash
git remote add origin git@github.com:YOUR_USERNAME/CarSparePartsManagementSystem.git
```

## Step 6: NetBeans Git Integration

### Enable Git in NetBeans:
1. Open NetBeans IDE
2. Go to **Tools** → **Options** → **Versioning** → **Git**
3. Ensure Git executable path is set correctly
4. Click **OK**

### Using Git in NetBeans:
1. Right-click on your project in the Projects window
2. Select **Git** → **Initialize Repository**
3. Or use **Team** → **Git** → **Initialize Repository**

### Commit Changes:
1. Right-click on project → **Git** → **Commit**
2. Select files to commit
3. Enter commit message
4. Click **Commit**

### Push to GitHub:
1. Right-click on project → **Git** → **Remote** → **Push**
2. Enter your GitHub credentials
3. Select branch to push (usually `main` or `master`)
4. Click **Next** and **Finish**

## Step 7: Daily Workflow

### Making Changes:
```bash
# Check status
git status

# Add changed files
git add .

# Or add specific files
git add src/dao/CategoryDaoImpl.java

# Commit changes
git commit -m "Description of changes"

# Push to GitHub
git push origin main
```

### Creating Branches (for features):
```bash
# Create and switch to new branch
git checkout -b feature/new-feature-name

# Make changes and commit
git add .
git commit -m "Add new feature"

# Push branch to GitHub
git push origin feature/new-feature-name

# Switch back to main branch
git checkout main

# Merge feature branch
git merge feature/new-feature-name
```

## Step 8: .gitignore File

The project already includes a `.gitignore` file that excludes:
- Compiled class files (*.class)
- Build directories (build/, dist/)
- IDE-specific files (nbproject/private/)
- Log files
- Temporary files

**Important**: Never commit:
- Database credentials
- Personal configuration files
- Build artifacts
- IDE private settings

## Troubleshooting

### Git not found:
- Ensure Git is installed and added to system PATH
- Restart terminal/IDE after installation

### Authentication issues:
- Use Personal Access Token instead of password
- Generate token: GitHub → Settings → Developer settings → Personal access tokens

### Merge conflicts:
- Pull latest changes: `git pull origin main`
- Resolve conflicts in IDE
- Commit resolved changes

## Best Practices

1. **Commit Often**: Make small, frequent commits with clear messages
2. **Write Good Commit Messages**: 
   - Use present tense: "Add feature" not "Added feature"
   - Be descriptive: "Fix SQL syntax error in SupplierDaoImpl" not "Fix bug"
3. **Pull Before Push**: Always pull latest changes before pushing
4. **Use Branches**: Create branches for new features or experiments
5. **Review Changes**: Use `git status` and `git diff` before committing

## Commit Message Examples

```
feat: Add category management functionality
fix: Fix SQL syntax error in SupplierDaoImpl update method
docs: Add JavaDoc to all DAO interfaces
refactor: Improve error handling in CategoryDaoImpl
style: Format code according to Google Java Style Guide
test: Add unit tests for Category model
```

## Additional Resources

- Git Documentation: https://git-scm.com/doc
- GitHub Guides: https://guides.github.com
- NetBeans Git Tutorial: https://netbeans.org/kb/docs/ide/git.html

## Next Steps

After setting up Git and GitHub:
1. ✅ Version Control System (GitHub) - **COMPLETED**
2. ⏭️ Software Design Pattern - Already implemented (MVC, DAO)
3. ⏭️ Testing Plan - Create test cases
4. ⏭️ Dockerization - Dockerize the application

---

**Note**: If you encounter any issues during setup, refer to the troubleshooting section or consult the Git documentation.

