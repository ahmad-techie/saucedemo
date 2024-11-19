# Test Automation Framework

## Overview

This is a Test Automation Framework built with **Java** and **Selenium**, designed to support automated testing for web applications. It includes capabilities for UI, API, and database testing to ensure smooth integration and data consistency across components. This framework also utilizes **TestNG** for test management and **Log4j2** for logging.

## Features

- **UI Testing**: Automates web interactions to test the User Interface using Selenium.
- **API Testing**: Tests REST APIs, allowing validation of the backend independent of the frontend.
- **Database Validation**: Ensures data consistency by connecting to the database and verifying records.
- **Reporting**: Generates HTML reports for test results.
- **Custom Listeners**: Implements custom listeners for better control and reporting, specifically using the `ITestResults` interface.
- **Logging**: Uses Log4j2 for efficient logging to aid in debugging and tracking.

## Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG** (for test management)
- **Log4j2** (for logging)
- **Extent Reports** (for reporting)
- **Maven** (for dependency management)

## Project Structure

```plaintext
project-root
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── base                # Contains BasePage class
│   │   │   ├── pages               # Page Object Model classes for UI elements
│   │   │   ├── api                 # API testing classes
│   │   │   ├── db                  # Database connection and verification classes
│   │   │   ├── utils               # Utility classes (e.g., data providers, helpers)
│   │   └── resources
│   │       ├── log4j2.xml          # Log4j2 configuration file
│           └── config.properties   # Contains project data such as base-url, browser type, etc.
│   ├── test
│       ├── java
│       │   ├── base                # Contains BaseTest class
│       │   ├── tests               # Test classes organized by module
│       │   ├── listeners           # Custom listeners implementing ITestResults
│       └── resources
│           └── config.properties   # TestNG suite configuration
├── testRunners                     # Xml suites, shell scripts and batch files
│   ├── batchFiles
│   ├── shellScripts
│   └── xml suite files
├── testOutput                
│   ├── reports
│   └── screenshots                 # Stores screenshots for smoke and regressions tests
├── logs                            # Stores log files
├── pom.xml                         # Maven configuration
├── README.md                       # Project documentation

```
## Installation
### Prerequisites
- **Java JDK** (version 8 or later)
- **Maven** (version 3.6.0 or later)
- **Browser** (e.g., Chrome, Firefox, Edge, Safari)

### Steps
1. **Clone the Repository**
   ```bash
   git clone https://github.com/ahmad-techie/saucedemo.git
   cd saucedemo
   mvn clean test