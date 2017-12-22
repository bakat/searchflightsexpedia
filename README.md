# GLOBOFORCE - Technical assessment

Author: Bernardo Vieira Carneiro

### Description

The project ***automated.test.search.flights*** contains a test automation for validating the behavior of the flight search from [EXPEDIA] (https://www.expedia.com/). It was built in Java, using the Selenium Webdriver for UI interaction, Cucumber for test scenario description and TestNG for test execution.

Internally you can find the project organized as follows:
- `src/main/java`: consists of a simple framework for automating tests in a more reusable and maintainable way;
- `src/main/resources`: contains a properties file within which some variables are set;
- `src/test/java`: contains the implementation of the tests, with page objects, step definitions and test classes;
- `src/test/resources`: contains a feature file with a scenario description;
- `browserDrivers`: contains the firefox driver.

### How to run the test

- From an IDE of your choice:
Import the project in your preferred IDE, open the class ***ListFlightResultsTest.java*** and click the RUN button. You can also right click the mentioned class and choose the option "Run As > TestNG Test".

- From maven:
From a command line tool, access the project "automated.test.search.flights" and then execute the following command: ***mvn surefire:test -Dtest=ListFlightResultsTest.java***

Although the project supports the test execution on multiple browsers, for this technical exercise it was only tested with the Firefox browser. Bear in mind that the version of the Firefox browser matters for test execution. Below follows a list containing the versions of the browser and libraries used.

### Versions of the libraries used

- TestNG: 6.11
- Selenium: 3.6.0
- Cucumber: 1.2.5
- Firefox browser: 57.0.1 (64-bit)
- Java version: 1.8
- OS: Linux
- Gecko driver: v0.19.0
