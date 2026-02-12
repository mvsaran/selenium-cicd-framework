# 🧪 Selenium CI/CD Continuous Testing Framework

> A robust, scalable Continuous Testing pipeline built with **Selenium**, **TestNG**, **Maven**, and **Jenkins** — executing Unit, Smoke, Sanity, Regression, and Integration suites independently for flaky-resistant, production-ready automation.

---

## 📸 Pipeline Overview

### Jenkins Pipeline Execution

<img width="1892" height="988" alt="Automatic Build" src="https://github.com/user-attachments/assets/5a52023c-7405-4338-ab0a-99600259139b" />

---

## 🚀 Features

- **Parallel Test Suite Execution** — All five test suites run independently in parallel within a single Jenkins pipeline stage
- **Modular TestNG XML Suites** — Each suite has its own configuration file (`testng-unit.xml`, `testng-smoke.xml`, etc.) for fine-grained control
- **JUnit Result Archiving** — Test results are archived automatically after every suite using `target/surefire-reports/*.xml`
- **Page Object Model (POM)** — Clean separation of test logic and UI interaction via `LoginPage` and `InventoryPage` page objects
- **Maven-driven Builds** — Full lifecycle management with `mvn test -DsuiteXmlFile=<suite>.xml`
- **Post-build Notifications** — Email reporting configured via Jenkins environment variables
- **Flaky-resistant Design** — Independent suite execution prevents cascading failures across test types

---

## 🗂️ Project Structure

```
CICDPIPELINE/
├── src/
│   ├── main/java/pages/
│   │   ├── InventoryPage.java
│   │   └── LoginPage.java
│   └── test/java/tests/
│       ├── UnitTest.java
│       ├── SmokeTest.java
│       ├── SanityTest.java
│       ├── RegressionTest.java
│       └── IntegrationTest.java
├── target/
│   ├── classes/pages/
│   └── test-classes/
├── testng-unit.xml
├── testng-smoke.xml
├── testng-sanity.xml
├── testng-regression.xml
├── testng-integration.xml
├── pom.xml
├── Jenkinsfile
└── README.md
```

---

## 🔧 Tech Stack

| Tool | Purpose |
|------|---------|
| **Selenium WebDriver** | Browser automation |
| **TestNG** | Test framework and suite management |
| **Maven** | Build automation and dependency management |
| **Jenkins** | CI/CD pipeline orchestration |
| **JUnit Reports** | Test result archiving and visualization |

---

## 💡 Why This Approach?

### ⚡ By the Numbers

| Metric | Value | What It Means |
|--------|-------|---------------|
| 🚀 Execution Speed | **~5× faster** | Total time = slowest suite only, not the sum of all |
| 🔒 Suite Interdependencies | **0** | Every suite runs fully isolated |
| 🧪 Test Layers | **5** | Unit → Smoke → Sanity → Regression → Integration |
| 🔁 Trigger | **1 pipeline run** | All 5 suites fire simultaneously on every commit |

---

### ⚔️ Sequential Pipeline vs This Framework

| | ❌ Sequential Pipeline | ✅ This Parallel Framework |
|---|---|---|
| **Speed** | Total time = sum of all suites | Total time = duration of the slowest suite |
| **Failure Impact** | One failure blocks all downstream suites | Failures are isolated — other suites continue |
| **Flaky Tests** | A flaky unit test can silently prevent integration tests from running | Flaky tests in one suite never affect another |
| **Failure Diagnosis** | Hard to pinpoint which layer broke when failures cascade | JUnit reports archived per suite — instant root cause |
| **Scalability** | Adding a suite means restructuring the whole pipeline | Add a TestNG XML + one parallel stage — done |
| **Feedback Speed** | Developers wait for every suite to finish sequentially | Fastest possible feedback on every commit |

---

### 🏆 Core Benefits

**⚡ 01 — Maximum Execution Speed**
All 5 suites run in parallel inside a single Jenkins stage. Wall-clock time equals the duration of the single longest suite — not the combined total. A pipeline that would take ~220 seconds sequentially completes in ~44 seconds.

**🔒 02 — Complete Suite Isolation**
Each suite runs in its own stage with a dedicated TestNG XML config file. A failure in Smoke Tests never prevents Regression or Integration tests from completing and reporting results.

**🎯 03 — Pinpoint Failure Attribution**
JUnit reports are archived independently per suite via `target/surefire-reports/*.xml`. You know exactly which layer broke and why within seconds of a build completing — no log archaeology needed.

**🧩 04 — Modular & Scalable Design**
Adding a new test suite requires only a new TestNG XML and a single parallel stage block in the Jenkinsfile. The architecture scales horizontally with zero refactoring of existing suites.

**🛡️ 05 — Flaky-Test Resistance**
Isolated execution means a flaky test in one suite cannot mask or trigger false failures elsewhere. The Page Object Model further reduces brittleness by decoupling UI selectors from test logic entirely.

**🔄 06 — Full Coverage Pyramid**
Unit → Smoke → Sanity → Regression → Integration mirrors the testing pyramid — ensuring both fast low-level checks and deep end-to-end validation run on every single commit automatically.

---

## 🧩 Pipeline Stages

```
Start → Checkout SCM → Run Test Suites (Parallel) → Post Actions → End
```

### Test Suites (Run in Parallel)

| Suite | TestNG XML | Command |
|-------|-----------|---------|
| Unit Tests | `testng-unit.xml` | `mvn test -DsuiteXmlFile=testng-unit.xml` |
| Smoke Tests | `testng-smoke.xml` | `mvn test -DsuiteXmlFile=testng-smoke.xml` |
| Sanity Tests | `testng-sanity.xml` | `mvn test -DsuiteXmlFile=testng-sanity.xml` |
| Regression Tests | `testng-regression.xml` | `mvn test -DsuiteXmlFile=testng-regression.xml` |
| Integration Tests | `testng-integration.xml` | `mvn test -DsuiteXmlFile=testng-integration.xml` |

---

## 📄 Jenkinsfile

```groovy
pipeline {
    agent any

    environment {
        RECIPIENT = 'your-email@example.com' // Replace with actual email
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Run Test Suites') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-unit.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Smoke Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-smoke.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Sanity Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-sanity.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Regression Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-regression.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Integration Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-integration.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            emailext(
                subject: "Build ${currentBuild.fullDisplayName} - ${currentBuild.result}",
                body: "Check the build at: ${env.BUILD_URL}",
                to: "${env.RECIPIENT}"
            )
        }
    }
}
```

---

## ⚙️ Prerequisites

- Java 8+
- Maven 3.6+
- Jenkins (with Pipeline and JUnit plugins)
- Chrome/Firefox WebDriver (or configured Grid)
- Git

---

## 🏃 Running Tests Locally

**Run all tests:**
```bash
mvn test
```

**Run a specific suite:**
```bash
mvn test -DsuiteXmlFile=testng-smoke.xml
mvn test -DsuiteXmlFile=testng-regression.xml
```

**Clean and run:**
```bash
mvn clean test -DsuiteXmlFile=testng-integration.xml
```

---

## 🔗 Jenkins Setup

1. Create a new **Pipeline** job in Jenkins
2. Point it to your Git repository
3. Set **Script Path** to `Jenkinsfile`
4. Configure your SMTP server under **Manage Jenkins → Configure System** for email notifications
5. Install required plugins: **Pipeline**, **JUnit**, **Email Extension**

---

## 📊 Test Results

After each pipeline run, test results are available under:
- **Jenkins UI** → Build → **Test Results**
- Archived reports at: `target/surefire-reports/*.xml`

---

## 👤 Author

**Saran Kumar**  
---

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
