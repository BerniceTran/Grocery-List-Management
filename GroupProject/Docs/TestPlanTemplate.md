# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Bernice Tran

## 1 Testing Strategy

### 1.1 Overall strategy

*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

For our Grocery List Manager application, we will employ various black-box and white-box testing techniques as follows:

**White-box Testing***

Unit Testing: Here, we will design an isolated test for every class being implemented to ensure that it is behaving exactly as expected. This will be performed by the developers.

Integration Testing: Here, we will test the interaction or combined pieces of code to ensure that aggregates of units perform accurately together. This will be performed by the developers.

System Testing: The objective of system testing is to identify defects that will only surface when a complete system is assembled. Here, because this will be a native Android application, we will test the application behavior with various Android environments/operating systems and hardware combinations by testing on emulated or real devices. This will be performed by quality assurance teams.

Regression Testing: Here, regression testing will only be done if new functionality is added to the application to ensure that the objective of regression testing is to check that old functionality has not been broken by new functionality or changes made in application. Like system testing, we will perform regression testing on emulated or real devices. This will be performed by quality assurance teams.


**Black-box Testing**

Functional Testing: Here, we will test the application against its specifications or use cases to ensure that the application is working as per the requirements. These Functional tests check that certain expected inputs result in expected outputs. Most of the test conducted for this is driven by the user interface and so will be performed by testers or quality assurance analysts.


### 1.2 Test Selection

*Here you should discuss how you are going to select your test cases, that is, which black-box and/or white-box techniques you will use. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

Because the application is built around specifications and requirements, a black-box test design technique we will use is use case testing. This is to especially used for functional testing to ensure that the application is working as per the requirements by a use case basis.


### 1.3 Adequacy Criterion

*Define how you are going to assess the quality of your test cases. Typically, this involves some form of functional or structural coverage. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

User interface testing will be done to ensure that our application meets its functional requirements. We will assess the quality of our test cases by using a human tester.

### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*

### 1.5 Technology

*Describe any testing technology you intend to use or build (e.g., JUnit, Selenium).*

Tools:
-Android SDK
-Android Studio (version)
-AndroidJUnitRunner
-JUnit 4 Rules
-Automation tool: Espresso (works with AndroidJUnitTestRunner)
-Functional testing: We will use a human tester and Espresso testing framework, provided by Android Testing Support Library, to simulate user interactions
-System testing: Android Virtual Device (AVD) manager in Android Studio where various emulated/virtual devices that mimic a wide range of device types and configurations can be used to test the app


## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*