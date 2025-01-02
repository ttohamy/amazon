# Amazon Automation Project

## Overview
This project automates the process of logging into Amazon Egypt, searching for newly listed items with free shipping, and adding products priced above 15,000 EGP to the cart.

---

## Tech Stack
- **Selenium** (Java)
- **Maven**
- **TestNG**
- **GitHub**

---

## Prerequisites
Before running this project, ensure the following are installed:
- **Java Development Kit (JDK)** (Version 8 or higher)
- **Selenium WebDriver**
- **Maven** (Build and dependency management)
- **IDE** (IntelliJ IDEA or Eclipse)
- **Git** (For version control)

---

## Features
- **Login Automation:** Logs in to Amazon Egypt with provided credentials.
- **Search and Filter Products:** Searches for new items with free shipping.
- **Price Filtering:** Adds items priced above 15,000 EGP to the cart.
- **Cart Validation:** Verifies the total cost of the items in the cart.
- **Custom Reporting:** Generates test execution reports.

---

## Installation
### 1. Clone the Repository
Make sure Git is installed, then open a terminal and run:
```
git clone https://github.com/ttohamy/amazon.git
```

### 2. Download Dependencies
Navigate to the project directory and execute:
```
mvn clean install
```
This downloads and installs all required dependencies specified in the **pom.xml** file.

### 3. Configure Credentials 
#### Since I am using the config.properties file to store critical information, please ensure that you follow these steps
1. Rename `config-sample.properties` to `config.properties`.
2. Update the file with your Amazon login credentials.

---

## How to Run
### Option 1: Using Maven Command
Run the following command in the terminal:
```
mvn clean test
```

### Option 2: Using IDE
1. Open the project in your IDE.
2. Locate the **TestNG.xml** file.
3. Right-click and select **Run**.

---

## Test Reporting
- Test results are generated automatically using **Extent Reports**.
- Open the report in your browser by navigating to:
```
test-output/ExtentReport/TestExecutionReport.html
```

---

## Contact
For any queries or support, reach out to "mohamed.eltohamy.ahmed@gmail.com".

