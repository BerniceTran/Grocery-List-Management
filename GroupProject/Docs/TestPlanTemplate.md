# Test Plan

**Author**: Bernice Tran

## 1 Testing Strategy

### 1.1 Overall strategy

For our Grocery List Manager application, we will employ various black-box and white-box testing techniques as follows:

**White-box Testing**

Unit Testing: Here, we will design an isolated test for every class being implemented to ensure that it is behaving exactly as expected. This will be performed by the developers.

Integration Testing: Here, we will test the interaction or combined pieces of code to ensure that aggregates of units perform accurately together. This will be performed by the developers.

System Testing: The objective of system testing is to identify defects that will only surface when a complete system is assembled. Here, because this will be a native Android application, we will test the application behavior with various Android environments/operating systems and hardware combinations by testing on emulated or real devices. This will be performed by quality assurance teams.

Regression Testing: Here, regression testing will only be done if new functionality is added to the application to ensure that old functionality has not been broken by new functionality or changes made in the application. Like system testing, we will perform regression testing on emulated or real devices. This will be performed by quality assurance teams.

**Black-box Testing**

Functional Testing: Here, we will test the application against its specifications or use cases to ensure that the application is working as per the requirements. These Functional tests check that certain expected inputs result in expected outputs. Most of the test conducted for this is driven by the user interface and so will be performed by testers or quality assurance analysts.


### 1.2 Test Selection

Because the application is built around specifications and requirements, a black-box test design technique we will use is use case testing. This is to especially used for functional testing to ensure that the application is working as per the requirements by a use case basis.

### 1.3 Adequacy Criterion

User interface testing will be done to ensure that our application meets its functional requirements. We will assess the quality of our test cases by using a human tester.

### 1.4 Bug Tracking

As we are using a human tester to test the functional aspects, we will also use a human tester to report bugs. We will capture bug reports directly from a device by using either **Take a bug report** developer option on the device, or through the Android Emulator menu. These bug reports contains device logs, stack traces, and other diagnostic information to help find and fix bugs.

### 1.5 Technology

Tools:  
- Android SDK  
- Android Studio  
- AndroidJUnitRunner  
- JUnit 4 Rules  
- Automation tool: Espresso (works with AndroidJUnitTestRunner)  
- Functional testing: We will use a human tester and Espresso testing framework, provided by Android Testing Support Library, to simulate user interactions  
- System testing: Android Virtual Device (AVD) manager in Android Studio where various emulated/virtual devices that mimic a wide range of device types and configurations can be used to test the app  

## 2 Test Cases

### Test Cases
[testCases]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/TestCases.png
![alt text][testCases]

### Test Steps
[testSteps]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/TestSteps.png
![alt text][testSteps]
