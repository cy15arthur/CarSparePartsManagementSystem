# PowerShell script to set up Git repository for Car Spare Parts Management System
# Run this script after installing Git

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Git Repository Setup Script" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if Git is installed
Write-Host "Checking if Git is installed..." -ForegroundColor Yellow
try {
    $gitVersion = git --version
    Write-Host "✓ Git is installed: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Git is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Git from: https://git-scm.com/download/win" -ForegroundColor Yellow
    Write-Host "After installation, restart PowerShell and run this script again." -ForegroundColor Yellow
    exit 1
}

Write-Host ""

# Check if repository is already initialized
if (Test-Path .git) {
    Write-Host "Git repository is already initialized." -ForegroundColor Yellow
    $response = Read-Host "Do you want to reinitialize? (y/n)"
    if ($response -ne "y") {
        Write-Host "Skipping initialization." -ForegroundColor Yellow
        exit 0
    }
}

# Initialize Git repository
Write-Host "Initializing Git repository..." -ForegroundColor Yellow
git init
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Git repository initialized successfully" -ForegroundColor Green
} else {
    Write-Host "✗ Failed to initialize Git repository" -ForegroundColor Red
    exit 1
}

Write-Host ""

# Check Git configuration
Write-Host "Checking Git configuration..." -ForegroundColor Yellow
$userName = git config user.name
$userEmail = git config user.email

if ([string]::IsNullOrEmpty($userName)) {
    Write-Host "Git user.name is not set." -ForegroundColor Yellow
    $name = Read-Host "Enter your name for Git commits"
    git config --global user.name $name
    Write-Host "✓ Git user.name set to: $name" -ForegroundColor Green
} else {
    Write-Host "✓ Git user.name: $userName" -ForegroundColor Green
}

if ([string]::IsNullOrEmpty($userEmail)) {
    Write-Host "Git user.email is not set." -ForegroundColor Yellow
    $email = Read-Host "Enter your email for Git commits"
    git config --global user.email $email
    Write-Host "✓ Git user.email set to: $email" -ForegroundColor Green
} else {
    Write-Host "✓ Git user.email: $userEmail" -ForegroundColor Green
}

Write-Host ""

# Add files to staging
Write-Host "Adding files to staging area..." -ForegroundColor Yellow
git add .
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Files added to staging area" -ForegroundColor Green
} else {
    Write-Host "✗ Failed to add files" -ForegroundColor Red
    exit 1
}

Write-Host ""

# Show status
Write-Host "Repository status:" -ForegroundColor Yellow
git status

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Next Steps:" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "1. Review the staged files above" -ForegroundColor White
Write-Host "2. Make your initial commit:" -ForegroundColor White
Write-Host "   git commit -m 'Initial commit: Car Spare Parts Management System'" -ForegroundColor Gray
Write-Host ""
Write-Host "3. Create a repository on GitHub:" -ForegroundColor White
Write-Host "   - Go to https://github.com" -ForegroundColor Gray
Write-Host "   - Create a new repository" -ForegroundColor Gray
Write-Host "   - Don't initialize with README" -ForegroundColor Gray
Write-Host ""
Write-Host "4. Connect to GitHub:" -ForegroundColor White
Write-Host "   git remote add origin https://github.com/YOUR_USERNAME/CarSparePartsManagementSystem.git" -ForegroundColor Gray
Write-Host "   git branch -M main" -ForegroundColor Gray
Write-Host "   git push -u origin main" -ForegroundColor Gray
Write-Host ""
Write-Host "For detailed instructions, see GITHUB_SETUP.md" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan

