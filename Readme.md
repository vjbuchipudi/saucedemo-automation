# SauceDemo Automation Framework

This is a Selenium WebDriver automation framework using Java, Maven, Cucumber BDD, and Page Object Model (POM) design pattern for testing the SauceDemo website.

## Project Structure

```
saucedemo-automation/
├── src/
│   └── test/
│       ├── java/
│       │   ├── pages/
│       │   │   ├── BasePage.java          # Base page with common WebDriver methods
│       │   │   ├── LoginPage.java         # Login page objects and methods
│       │   │   └── ProductsPage.java      # Products page objects and methods
│       │   ├── stepdefinitions/
│       │   │   └── ShoppingSteps.java     # Cucumber step definitions
│       │   └── runners/
│       │       └── TestRunner.java        # Cucumber test runner
│       └── resources/
│           └── features/
│               └── Shopping.feature       # Cucumber feature file with scenarios
├── pom.xml                                # Maven dependencies and build configuration
└── README.md                              # This file
```

## Technologies Used

- **Java 11**: Programming language
- **Maven**: Build and dependency management
- **Selenium WebDriver 4.16.1**: Browser automation
- **Cucumber 7.15.0**: BDD framework
- **JUnit 4.13.2**: Test runner
- **WebDriverManager 5.6.3**: Automatic driver management

## Prerequisites

1. Java JDK 11 or higher installed
2. Maven 3.6 or higher installed
3. Chrome browser installed
4. Internet connection (for downloading dependencies)

## Setup Instructions

1. **Clone or extract the project**
   ```bash
   cd /path/to/saucedemo-automation
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run the tests**
   ```bash
   mvn test
   ```

## Test Scenarios

The framework includes the following test scenarios:

1. **Successfully login and add t-shirt to cart**
   - Navigate to SauceDemo website
   - Login with valid credentials (standard_user/secret_sauce)
   - Verify Products page is displayed
   - Add "Sauce Labs Bolt T-Shirt" to cart
   - Verify cart shows 1 item

2. **Verify multiple products are displayed after login**
   - Login successfully
   - Verify 6 products are displayed on the products page

3. **Login with invalid credentials**
   - Attempt login with invalid credentials
   - Verify error message is displayed

## Running Specific Tests

To run specific scenarios using tags:
```bash
mvn test -Dcucumber.filter.tags="@tag_name"
```

## Test Reports

After running tests, reports are generated in:
- HTML Report: `target/cucumber-reports/cucumber.html`
- JSON Report: `target/cucumber-reports/cucumber.json`
- XML Report: `target/cucumber-reports/cucumber.xml`

## Page Object Model (POM)

The framework uses POM design pattern for better maintainability:

- **BasePage**: Contains common WebDriver operations (click, sendKeys, wait, etc.)
- **LoginPage**: Contains login page elements and methods
- **ProductsPage**: Contains products page elements and methods

## Configuration

### Headless Mode
To run tests in headless mode, uncomment the following line in `ShoppingSteps.java`:
```java
options.addArguments("--headless");
```

### Browser Options
Chrome options are configured in the `setUp()` method of `ShoppingSteps.java`:
- Remote origins allowed
- Dev shm usage disabled
- Sandbox disabled (for CI/CD environments)

## Troubleshooting

1. **ChromeDriver version mismatch**: WebDriverManager automatically handles this
2. **Tests fail to start**: Ensure Java and Maven are properly installed
3. **Element not found**: Increase implicit wait time in `ShoppingSteps.java`
4. **Port already in use**: Close any running browser instances

## Extending the Framework

To add new test scenarios:
1. Add new scenarios to `Shopping.feature` or create new feature files
2. Implement corresponding step definitions in `ShoppingSteps.java` or create new step definition classes
3. Create new page objects if testing new pages

## Author

Created as a demo automation framework for SauceDemo website testing.