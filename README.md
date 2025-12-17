# Car Spare Parts Management System

A comprehensive desktop application for managing car spare parts inventory, customers, suppliers, categories, and sales transactions.

## Project Overview

This project is a Java-based desktop application built using Swing GUI framework and MySQL database. It implements the MVC (Model-View-Controller) and DAO (Data Access Object) design patterns to ensure clean code architecture and separation of concerns.

## Features

- **Category Management**: Create, update, delete, and search categories
- **Part Management**: Manage spare parts with details like name, brand, price, stock quantity
- **Customer Management**: Maintain customer information and contact details
- **Supplier Management**: Track supplier information and contact details
- **Sales Management**: Record and track sales transactions
- **Database Integration**: MySQL database for persistent data storage

## Technology Stack

- **Programming Language**: Java 8
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **Design Patterns**: MVC, DAO
- **IDE**: NetBeans
- **Build Tool**: Ant (via NetBeans)

## Project Structure

```
CarSparePartsManagementSystem/
├── src/
│   ├── dao/              # Data Access Object interfaces and implementations
│   ├── model/            # Entity classes (Category, Customer, Part, Supplier, Sales)
│   ├── util/             # Utility classes (DatabaseConnectionManager)
│   └── view/             # GUI components (Swing forms)
├── build/                 # Compiled class files
├── dist/                  # Distribution files (JAR)
├── nbproject/             # NetBeans project configuration
└── test/                  # Test files

```

## Design Patterns Implemented

### 1. MVC (Model-View-Controller)
- **Model**: Entity classes in `model/` package
- **View**: Swing GUI components in `view/` package
- **Controller**: Business logic integrated in view components

### 2. DAO (Data Access Object)
- **Interfaces**: Defined in `dao/` package (e.g., `CategoryDao`, `CustomerDao`)
- **Implementations**: Concrete implementations in `dao/` package (e.g., `CategoryDaoImpl`, `CustomerDaoImpl`)
- Provides abstraction layer between business logic and database operations

## Database Schema

The application uses a MySQL database named `car_spare_parts_db` with the following tables:
- `categories`: Category information
- `customers`: Customer details
- `parts`: Spare parts inventory
- `suppliers`: Supplier information
- `sales`: Sales transaction records

## Setup Instructions

### Prerequisites
- Java JDK 8 or higher
- MySQL Server
- NetBeans IDE (recommended) or any Java IDE
- MySQL Connector/J driver

### Database Setup
1. Create a MySQL database named `car_spare_parts_db`
2. Create the required tables (categories, customers, parts, suppliers, sales)
3. Update database credentials in `src/util/DatabaseConnectionManager.java` if needed

### Running the Application
1. Clone this repository
2. Open the project in NetBeans IDE
3. Build the project (Clean and Build)
4. Run the `HomePage.java` class as the main entry point

## Coding Standards

This project follows **Google Java Style Guide** standards:
- Proper error handling with logging
- Comprehensive JavaDoc documentation
- Proper resource management (try-with-resources)
- Consistent code formatting
- No code duplication
- Meaningful variable and method names

## Version Control

This project uses **Git** for version control and is configured for **GitHub** integration.

### Setup Instructions
1. **Install Git**: Download from https://git-scm.com/download/win
2. **Run Setup Script**: Execute `setup-git.ps1` in PowerShell
3. **Create GitHub Repository**: Follow instructions in `GITHUB_SETUP.md`
4. **Connect and Push**: Link local repository to GitHub

### Quick Start
See `QUICK_START.md` for a quick 5-step setup guide.

### Git Workflow
- Main branch: `main` or `master`
- Feature branches for new features
- Commit messages follow conventional commit format

### NetBeans Integration
- Git is integrated into NetBeans IDE
- Use Team → Git menu for version control operations
- See `GITHUB_SETUP.md` for detailed NetBeans Git instructions

## Testing

Test cases and testing plan will be implemented to ensure application reliability and correctness.

## Dockerization

The application will be dockerized for easy deployment and distribution.

## Author

cyita

## License

This project is developed for educational purposes as part of a final exam project.

## Future Enhancements

- Unit testing with JUnit
- Docker containerization
- Enhanced error handling and user feedback
- Report generation features
- User authentication and authorization

