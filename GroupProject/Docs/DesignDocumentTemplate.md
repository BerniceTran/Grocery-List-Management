# Design Document

*This is the template for your design document. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author(s)**: **Team 6**

* *Nazib Mondal*
* *Ricardo Delahoz*
* *Bernice Tran*
* *Jooyong "Daniel" Park*

## 1 Design Considerations

*The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.*

[//]: # (The interaction between the software and the items database -GroceryStoreDatabase- needs to be further defined on a lower level. As it stands we are using an imaginary database and have yet to properly implement the way the database would work. The design itself also does not reflect how the UI would be handled.)

### 1.1 Assumptions

*Describe any assumption, background, or dependencies of the software, its use, the operational environment, or significant project issues.*

The design assumes the items database is working and negligible in its implementation; it assumes that the database is fully functional and can easily communicate between the software API and the database itself. The design also assumes that there is enough budget to handle the implementation and design of a database in the case that the team needs to build a database. The design is assumed to work only for the Android platform, but can possibly be used for porting to other platforms in the future - which is not the team's main focus at the current time.

### 1.2 Constraints

*Describe any constraints on the system that have a significant impact on the design of the system.*

The budget and time constraints imposed for the creation of the design could have caused a flaw somewhere that might have been overlooked. As it stands however, the only constraints for the system are the platform to which the design is to be serving -which is the Android platform- and the implementation of the database that will be used with the software itself.

### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*

The hardware components the design is to be implemented on is for devices that are running the Android platform. This primarily consists of hardware that belong to the mobile device group, including phones, tablets, and laptops that might be using Android emulators. The software that this design will be working with is primarily Android and the features that come with Android development including SQLite for database interactions, as well as XML for UI design. 

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram

*This section should provide and describe a diagram that shows the various components and how they are connected. This diagram shows the logical/functional components of the system, where each component represents a cluster of related functionality. In the case of simple systems, where there is a single component, this diagram may be unnecessary; in these cases, simply state so and concisely state why.*

### 2.2 Deployment Diagram

*This section should describe how the different components will be deployed on actual hardware devices. Similar to the previous subsection, this diagram may be unnecessary for simple systems; in these cases, simply state so and concisely state why.*

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

*In the case of an OO design, the internal structure of a software component would typically be expressed as a UML class diagram that represents the static class structure for the component and their relationships.*

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*
