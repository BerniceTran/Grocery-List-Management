[tests]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/TestMatrix.png
[excel-spreadsheet]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/diagramPDF/testCaseSpreadsheet.xlsx
# Test Plan

**Author(s)**: Bernice Tran, Nazib Mondal

## 1 Testing Strategy

### 1.1 Overall strategy

For our Grocery List Manager application, we will employ various black-box and white-box testing techniques as follows:

**White-box Testing v1.4**

Unit Testing: Here, we will design an isolated test for every non-UI class being implemented to ensure that it is behaving exactly as expected. This will be performed by the developers. Most of the white-box testing tools for Unit Testing will be handled through the usage of automation using Android Studio's built-in unit tester.

Integration Testing: Here, we will test the interaction or combined pieces of code to ensure that aggregates of units perform accurately together. This will be performed by the developers.

System Testing: The objective of system testing is to identify defects that will only surface when a complete system is assembled. Here, because this will be a native Android application, we will test the application behavior with various Android environments/operating systems and hardware combinations by testing on emulated or real devices. This will be performed by quality assurance teams.

Regression Testing: Here, regression testing will only be done if new functionality is added to the application to ensure that old functionality has not been broken by new functionality or changes made in the application. Like system testing, we will perform regression testing on emulated or real devices. This will be performed by quality assurance teams. Regression testing will also be implemented when a bug is found in the current live version of the app, and whenever changes to the code to handle the bug occurs; this may include the modification of legacy code, to which Regression Testing will occur again by running the newly fixed system against the original test cases.

**Black-box Testing v1.4**

Functional Testing: Here, we will test the application against its specifications or use cases to ensure that the application is working as per the requirements. These Functional tests check that certain expected inputs result in expected outputs. Most of the test conducted for this is driven by the user interface and so will be performed by testers or quality assurance analysts. The majority of UI testing and use-case testing will be done purely through black-box testing. Some exceptions to this will be handled through the "Search()" method that will be implemented within the GroceryItem sub-menu, which will be handled with white-box testing. The majority of black-box tests will be handled manually as opposed to the white-box counterparts.


### 1.2 Test Selection v1.2

Because the application is built around specifications and requirements, a black-box test design technique we will use is use case testing. This is used for functional testing to ensure that the application is working as per the requirements by a use case basis.

### 1.3 Adequacy Criterion v1.3

User interface testing will be done to ensure that our application meets its functional requirements. We will assess the quality of our test cases by using a human tester. This is to be done manually through the usage of black-box testing.

### 1.4 Bug Tracking

As we are using a human tester to test the functional aspects, we will also use a human tester to report bugs. We will capture bug reports directly from a device by using either **Take a bug report** developer option on the device, or through the Android Emulator menu. These bug reports contain device logs, stack traces, and other diagnostic information to help find and fix bugs.

### 1.5 Technology v1.5

Tools:  
- Android SDK  
- Android Studio  
- AndroidJUnitRunner  
- JUnit 4 Rules  
- Automation tool: Espresso (works with AndroidJUnitTestRunner)  
- Functional testing: We will use a human tester and Espresso testing framework, provided by Android Testing Support Library, to simulate user interactions  
- System testing: Android Virtual Device (AVD) manager in Android Studio where various emulated/virtual devices that mimic a wide range of device types and configurations can be used to test the app  
- UI testing will be handled manually through the usage of a human tester, and the tests will be cataloged via Test Matrix ([excel-spreadsheet])

## 2 Test Cases

### Test Cases v1.5

Each test case has a unique test case ID denoted by TCID followed by a step-by-step procedure denoted by SID, corresponding to its TCID. The test cases can be read as follows:

- Test cases that are bolded and blue are test cases that have newly passed with the current version of the app
- Test cases that are marked in red are test cases that have persistently failed up to the current version of the app
- Test cases that are marked in red, bolded, and italicized, are NEWLY failed test cases (that previously passed) in the current version of the app

![alt text][tests]
