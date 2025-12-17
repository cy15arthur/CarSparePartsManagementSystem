# Test Plan - Car Spare Parts Management System

## 1. Introduction

### 1.1 Purpose
This document outlines the testing strategy and test cases for the Car Spare Parts Management System. The purpose is to ensure the application meets all functional requirements and operates correctly.

### 1.2 Scope
This test plan covers:
- Unit testing of Model classes
- Unit testing of DAO classes
- Integration testing
- Functional testing
- User interface testing

### 1.3 Test Environment
- **Operating System**: Windows 10/11
- **Java Version**: JDK 8 or higher
- **Database**: MySQL
- **Testing Framework**: JUnit 4
- **IDE**: NetBeans

---

## 2. Test Strategy

### 2.1 Testing Levels

#### Unit Testing
- Test individual components in isolation
- Test Model classes (Category, Customer, Part, Supplier, Sales, User)
- Test DAO implementations
- Test utility classes

#### Integration Testing
- Test interaction between components
- Test DAO with database
- Test Model-DAO integration

#### System Testing
- Test complete system functionality
- Test user workflows
- Test error handling

### 2.2 Test Types

#### Functional Tests
- CRUD operations (Create, Read, Update, Delete)
- Search functionality
- Data validation
- Business logic

#### Non-Functional Tests
- Performance
- Usability
- Error handling
- Security

---

## 3. Test Cases

### 3.1 Model Classes Tests

#### Category Model
- ✅ Default constructor test
- ✅ Parameterized constructor test
- ✅ Getter and setter tests
- ✅ Equals method test
- ✅ HashCode method test
- ✅ ToString method test

#### Customer Model
- ✅ Default constructor test
- ✅ Parameterized constructor test
- ✅ Getter and setter tests
- ✅ Equals method test
- ✅ HashCode method test
- ✅ ToString method test

#### Part Model
- ✅ Default constructor test
- ✅ Parameterized constructor test
- ✅ Getter and setter tests
- ✅ Equals method test
- ✅ HashCode method test
- ✅ ToString method test

#### Supplier Model
- ✅ Default constructor test
- ✅ Parameterized constructor test
- ✅ Getter and setter tests
- ✅ Equals method test
- ✅ HashCode method test
- ✅ ToString method test

#### Sales Model
- ✅ Default constructor test
- ✅ Parameterized constructor test
- ✅ Getter and setter tests
- ✅ Equals method test
- ✅ HashCode method test
- ✅ ToString method test

#### User Model
- ✅ Default constructor test
- ✅ Parameterized constructor test
- ✅ Getter and setter tests
- ✅ IsAdmin method test
- ✅ Equals method test
- ✅ HashCode method test
- ✅ ToString method test

### 3.2 DAO Classes Tests

#### CategoryDao Tests
- ✅ Create category test
- ✅ Create null category test (negative)
- ✅ Search by ID test
- ✅ Search non-existent ID test (negative)
- ✅ Search with null ID test (negative)
- ✅ Update category test
- ✅ Update null category test (negative)
- ✅ Delete category test
- ✅ Delete non-existent category test (negative)
- ✅ Delete with null ID test (negative)
- ✅ Display all categories test

#### CustomerDao Tests
- ✅ Create customer test
- ✅ Search by ID test
- ✅ Update customer test
- ✅ Delete customer test
- ✅ Display all customers test

#### PartDao Tests
- ✅ Create part test
- ✅ Search by ID test
- ✅ Update part test
- ✅ Delete part test
- ✅ Display all parts test

#### SupplierDao Tests
- ✅ Create supplier test
- ✅ Search by ID test
- ✅ Update supplier test
- ✅ Delete supplier test
- ✅ Display all suppliers test

#### SalesDao Tests
- ✅ Create sales test
- ✅ Search by ID test
- ✅ Update sales test
- ✅ Delete sales test
- ✅ Display all sales test

#### UserDao Tests
- ✅ Authenticate user test
- ✅ Authenticate with wrong credentials test (negative)
- ✅ Create user test
- ✅ Find by username test
- ✅ Username exists test
- ✅ Update user test

### 3.3 Integration Tests

#### Database Integration
- ✅ Database connection test
- ✅ CRUD operations with database
- ✅ Transaction handling
- ✅ Error handling for database failures

#### Model-DAO Integration
- ✅ Category creation and retrieval
- ✅ Customer creation and retrieval
- ✅ Part creation and retrieval
- ✅ Supplier creation and retrieval
- ✅ Sales creation and retrieval
- ✅ User authentication flow

### 3.4 UI Tests (Manual)

#### Login Screen
- ✅ Valid login test
- ✅ Invalid credentials test
- ✅ Empty fields validation
- ✅ Navigation after login

#### Category Management
- ✅ Add category
- ✅ Update category
- ✅ Delete category
- ✅ Search category
- ✅ Display all categories
- ✅ Form validation

#### Part Management
- ✅ Add part
- ✅ Update part
- ✅ Delete part
- ✅ Search part
- ✅ Display all parts
- ✅ Form validation

#### Customer Management
- ✅ Add customer
- ✅ Update customer
- ✅ Delete customer
- ✅ Search customer
- ✅ Display all customers
- ✅ Form validation

#### Supplier Management
- ✅ Add supplier
- ✅ Update supplier
- ✅ Delete supplier
- ✅ Search supplier
- ✅ Display all suppliers
- ✅ Form validation

#### Sales Management
- ✅ Add sales record
- ✅ Update sales record
- ✅ Delete sales record
- ✅ Search sales record
- ✅ Display all sales
- ✅ Form validation

---

## 4. Test Execution

### 4.1 Running Unit Tests

#### Using NetBeans:
1. Right-click on test file
2. Select "Run File" or "Test File"
3. View results in Test Results window

#### Using Command Line:
```bash
# Navigate to project directory
cd D:\Documents\NetBeansProjects\CarSparePartsManagementSystem

# Run all tests
ant test

# Run specific test
ant test-single -Dtest.includes=**/CategoryTest.java
```

### 4.2 Test Results

Test results are stored in:
- `build/test/results/` - Test execution results
- `build/test/results/html/` - HTML test reports

---

## 5. Test Data

### 5.1 Test Database Setup

Before running tests, ensure:
1. Test database is created
2. Tables are created with proper schema
3. Test data is inserted (optional, for integration tests)

### 5.2 Sample Test Data

#### Categories
- CAT001: Engine Parts
- CAT002: Brake Parts
- CAT003: Transmission Parts

#### Customers
- CUST001: John Doe, john@email.com, 123-456-7890
- CUST002: Jane Smith, jane@email.com, 098-765-4321

#### Parts
- P001: Brake Pad, Bosch, $50.00, Stock: 100
- P002: Oil Filter, Fram, $25.00, Stock: 50

#### Suppliers
- SUP001: ABC Parts, abc@parts.com, 123 Main St
- SUP002: XYZ Auto, xyz@auto.com, 456 Oak Ave

#### Users
- admin / admin123 (Admin role)
- staff / staff123 (Staff role)

---

## 6. Defect Management

### 6.1 Defect Severity Levels

- **Critical**: System crash, data loss
- **High**: Major functionality broken
- **Medium**: Minor functionality issue
- **Low**: Cosmetic issue, typo

### 6.2 Defect Reporting

For each defect found:
1. Document test case that failed
2. Describe expected vs actual behavior
3. Include steps to reproduce
4. Assign severity level
5. Track resolution

---

## 7. Test Coverage

### 7.1 Coverage Goals

- **Model Classes**: 100% method coverage
- **DAO Classes**: 80%+ method coverage
- **Utility Classes**: 90%+ method coverage

### 7.2 Coverage Tools

- NetBeans built-in coverage tool
- JaCoCo (if integrated)

---

## 8. Test Schedule

### Phase 1: Unit Tests (Week 1)
- Model classes tests
- DAO classes tests
- Utility classes tests

### Phase 2: Integration Tests (Week 2)
- Database integration
- Component integration

### Phase 3: System Tests (Week 3)
- End-to-end workflows
- UI testing
- Performance testing

### Phase 4: Regression Tests (Week 4)
- Re-test after fixes
- Final validation

---

## 9. Test Deliverables

1. ✅ Test Plan (this document)
2. ✅ Unit Test Cases (JUnit test files)
3. ✅ Integration Test Cases
4. ✅ Test Execution Reports
5. ✅ Defect Reports
6. ✅ Test Summary Report

---

## 10. Risk Assessment

### 10.1 Testing Risks

| Risk | Impact | Mitigation |
|------|--------|------------|
| Database unavailable | High | Use test database, mock connections |
| Test data corruption | Medium | Use separate test database |
| Time constraints | Medium | Prioritize critical tests |
| Incomplete requirements | High | Clarify with stakeholders |

---

## 11. Success Criteria

Tests are considered successful when:
- ✅ All unit tests pass
- ✅ All integration tests pass
- ✅ No critical defects found
- ✅ Code coverage meets targets
- ✅ Application functions as specified

---

## 12. Appendix

### A. Test Environment Setup
See `README.md` for environment setup instructions.

### B. Database Schema
See database setup scripts for table structures.

### C. Test Tools
- JUnit 4
- NetBeans IDE
- MySQL Database

---

**Document Version**: 1.0  
**Last Updated**: 2024  
**Author**: cyita

