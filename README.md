# Spring Boot Authentication Series

Welcome to the **Spring Boot Authentication Series** repository! This project demonstrates various authentication methods in a Spring Boot application. 

## Branches Overview

### 1. **Main Branch**
This branch contains the minimal setup for the project. It serves as the starting point for the authentication implementations. Use this branch to understand the foundational structure before diving into specific authentication methods.(it's an empty project with the dependancies added)

### 2. **Form-Login Branch**
The `form-login` branch demonstrates how to implement traditional form-based login authentication using Spring Security. It includes:
- Login and logout functionality.
- Basic security configurations.

### 3. **JWT-Login Branch**
The `jwt-login` branch showcases how to implement JSON Web Token (JWT)-based authentication. It includes:
- Token generation and validation.
- Secure REST API endpoints.

## Getting Started
Clone the repository and switch to the desired branch to explore the specific authentication setup:

```bash
# Clone the repository
git clone https://github.com/madegwa-o/springboot-authentication-series.git

# Switch to a branch
git checkout form-login  # For form-based login
# or
git checkout jwt-login   # For JWT-based login
