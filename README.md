# Saucedemo E2E Selenium Test Automation

## Project Structure
- src/main/java/pages: Page Object Model classes
- src/test/java/tests: TestNG test classes (smoke, sanity, regression, unit, integration)
- testng-*.xml: TestNG suite files for each tag
- Jenkinsfile: CI pipeline for running tagged tests and sending email notifications

## Jenkins Integration
- Each test group runs in a separate pipeline stage
- Email notification sent after each run with metrics

## Setup
1. Install Maven, Java, ChromeDriver
2. Configure Jenkins with Email Extension plugin
3. Update Jenkinsfile RECIPIENT with your email

## Run Locally
- mvn test -DsuiteXmlFile=testng-smoke.xml
- mvn test -DsuiteXmlFile=testng-sanity.xml
- mvn test -DsuiteXmlFile=testng-regression.xml
- mvn test -DsuiteXmlFile=testng-unit.xml
- mvn test -DsuiteXmlFile=testng-integration.xml

## Customization
- Add more tests or tags as needed
- Update Jenkinsfile for additional notifications or metrics
