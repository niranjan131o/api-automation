# API Automation Framework

## Overview
This API automation framework is built using **RestAssured**, **TestNG**, and **Allure Reports** to facilitate testing of RESTful APIs. The framework is designed to ensure maintainability, scalability, and ease of execution.

## Project Structure
```
api-automation/
│-- .idea/                      # IDE-specific settings
│-- allure-results/             # Allure reports output
│-- src/
│   ├── main/
│   │   └── java/
│   │       └── utils/          # Utility classes
│   ├── test/
│   │   └── java/
│   │       ├── base/           # Base test setup
│   │       ├── data/           # Test data
│   │       ├── tests/          # Test cases
│   │       └── utils/          # Test utilities
│-- target/                     # Build output
│-- LICENSE                     # License file
│-- pom.xml                      # Maven dependencies
│-- README.md                    # Documentation
│-- testng.xml                    # TestNG configuration
```

## Technologies Used
- **Java 21.0.6**
- **Maven** 3.9.9(Dependency management)
- **RestAssured** (API testing)
- **TestNG** (Test execution and assertions)
- **Allure Reports** (Test reporting)
- **Jackson Databind** (JSON parsing)

## Prerequisites
Ensure that the following are installed:
- **Java 21.0.6**
- **Maven 3.9.9**
- **IntelliJ IDEA** (Recommended IDE)

## Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd api-automation
   ```
2. **Install dependencies:**
   ```bash
   mvn clean install
   ```
3. **Run API tests:**
   ```bash
   mvn test
   ```
5. **Serve Allure Reports:**
   ```bash
   allure serve 
   ```

## Test Configuration
- **Base URL:** `https://api.football-data.org/`
- **Endpoints:**
    - `/v4/areas/` - Fetches list of areas

## Running Tests via TestNG
Tests can be run using the **testng.xml** file:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Logging and Assertions
- **Logging:** Allure is used for step-wise logging.
- **Assertions:** Status codes, JSON structure validation.

## Extending the Framework
1. **Adding new tests:** Create a new test class under `src/test/java/tests/`.
2. **Adding new utilities:** Define reusable methods in `src/main/java/utils/`.

## Reporting
- Reports are generated using **Allure**.
- JSON responses are logged in the reports.

## License
This project is licensed under the **Author Niranjan**.

## Contact
For any queries, reach out to the maintainers of this repository @Niranjan.

