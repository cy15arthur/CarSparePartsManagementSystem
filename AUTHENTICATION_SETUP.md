# Authentication System Setup Guide

## Overview
A complete authentication system has been added to the Car Spare Parts Management System with:
- **Login Page**: Modern, professional login interface
- **Admin Dashboard**: Dedicated admin interface with system overview
- **User Management**: User model and DAO for authentication
- **Role-Based Access**: Support for admin, manager, and staff roles

## Components Created

### 1. Model Layer
- **`User.java`**: User entity with authentication fields (username, password, role, etc.)

### 2. DAO Layer
- **`UserDao.java`**: Interface for user operations
- **`UserDaoImpl.java`**: Implementation with authentication logic

### 3. View Layer
- **`Login.java`**: Modern login interface
- **`AdminDashboard.java`**: Admin dashboard with navigation cards

### 4. Database
- **`DATABASE_SETUP_USERS.sql`**: SQL script to create users table

## Database Setup

### Step 1: Create Users Table
Run the SQL script `DATABASE_SETUP_USERS.sql` in your MySQL database:

```sql
CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) NOT NULL DEFAULT 'staff',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Step 2: Insert Default Admin User
The SQL script includes default users:
- **Admin**: username=`admin`, password=`admin123`
- **Staff**: username=`staff`, password=`staff123`

**⚠️ IMPORTANT**: In production, passwords should be hashed using BCrypt or similar!

## Application Flow

### Without Authentication (Current)
```
Application Start → HomePage → Management Views
```

### With Authentication (New)
```
Application Start → Login → AdminDashboard/HomePage → Management Views
```

## Login Credentials

### Default Admin Account
- **Username**: `admin`
- **Password**: `admin123`
- **Role**: `admin`

### Default Staff Account
- **Username**: `staff`
- **Password**: `staff123`
- **Role**: `staff`

## Features

### Login Page
- ✅ Modern, professional design
- ✅ Username and password fields
- ✅ Error handling and validation
- ✅ Role-based navigation (admin → AdminDashboard, others → HomePage)

### Admin Dashboard
- ✅ Welcome message with user info
- ✅ Navigation cards for all modules
- ✅ Logout functionality
- ✅ Modern card-based layout

### User Management
- ✅ Authentication method
- ✅ User creation and update
- ✅ Username existence checking
- ✅ Role-based access control

## Security Considerations

### Current Implementation
- Passwords are stored in plain text (for development only)
- Basic authentication without encryption

### Production Recommendations
1. **Password Hashing**: Use BCrypt or Argon2
2. **Session Management**: Implement proper session handling
3. **Password Policy**: Enforce strong password requirements
4. **Account Lockout**: Implement after failed login attempts
5. **Audit Logging**: Log all authentication attempts
6. **Two-Factor Authentication**: For sensitive operations

## Usage

### Starting the Application
The application now starts with the Login page. Users must authenticate before accessing the system.

### Admin Access
- Admins are redirected to `AdminDashboard` after login
- Admin dashboard provides quick access to all modules
- Logout button returns to login screen

### Staff Access
- Staff users are redirected to `HomePage` after login
- Standard access to management modules

## Integration Points

### HomePage Integration
- Updated to check authentication status
- Redirects to Login if not authenticated

### All Management Views
- Currently accessible without re-authentication
- Can be enhanced with session checking if needed

## Signup Page - Is It Necessary?

### Recommendation: **Not Required for This System**

**Reasons:**
1. **Internal System**: This is a management system for internal use, not a public application
2. **Admin-Controlled**: User accounts should be created by administrators
3. **Security**: Self-registration can be a security risk
4. **Audit Trail**: Admin-created accounts provide better audit trails

### Alternative: User Management Module
Instead of a signup page, consider adding:
- **User Management View**: For admins to create/manage user accounts
- **User Registration Form**: Within the admin dashboard
- **Bulk User Import**: For adding multiple users

## Next Steps (Optional Enhancements)

1. **User Management View**: Create/Update/Delete users
2. **Password Reset**: Forgot password functionality
3. **Session Management**: Auto-logout after inactivity
4. **Permission System**: Fine-grained permissions per role
5. **Activity Logging**: Track user actions
6. **Password Hashing**: Implement BCrypt for production

## Testing

### Test Login
1. Start the application
2. Login page should appear
3. Enter `admin` / `admin123`
4. Should redirect to AdminDashboard
5. Test logout functionality

### Test Access Control
1. Login as staff user
2. Should redirect to HomePage (not AdminDashboard)
3. Verify all modules are accessible

## Troubleshooting

### Login Fails
- Check if users table exists
- Verify default users were inserted
- Check database connection
- Review logs for SQL errors

### Cannot Access After Login
- Verify user role is set correctly
- Check if user is active (`is_active = 1`)
- Review authentication logic in UserDaoImpl

---

**Status**: ✅ Authentication system implemented and ready for use
**Next**: Run `DATABASE_SETUP_USERS.sql` to create the users table

