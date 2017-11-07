[components]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/componentDiagram.png?raw=true "Components Diagram - Nazib Mondal"
[low-level-class]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Team/teamDesign.PNG "Class Diagram - Team Design"
[UI]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designUI.png?raw=true "UI Design - Nazib Mondal"
# Design Document

**Author(s)**: **Team 6**

* *Nazib Mondal*
* *Ricardo Delahoz*
* *Bernice Tran*
* *Jooyong "Daniel" Park*

## 1 Design Considerations

[//]: # (This is just a comment - The interaction between the software and the items database -GroceryStoreDatabase- needs to be further defined on a lower level. As it stands we are using an imaginary database and have yet to properly implement the way the database would work. The design itself also does not reflect how the UI would be handled.)

### 1.1 Assumptions

* The design assumes the items database is working and negligible in its implementation.
* It assumes that the database is fully functional and can easily communicate between the software API and the database itself.
* The design also assumes that there is enough budget to handle the implementation and design of a database in the case that the team needs to build a database.
* The design is assumed to work only for the Android platform, but can possibly be used for porting to other platforms in the future - which is not the team's main focus at the current time.

### 1.2 Constraints

* The budget and time constraints imposed for the creation of the design could have caused a flaw somewhere that might have been overlooked.
* As it stands however, the only constraints for the system are the platform to which the design is to be serving -which is the Android platform- and the implementation of the database that will be used with the software itself.

### 1.3 System Environment

* The hardware components the design is to be implemented on is for devices that are running the Android platform.
* This primarily consists of hardware that belong to the mobile device group, including:

 * Phones
 * Tablets
 * Laptops that might be using an Android emulators

* The software that this design will be working with is primarily Android and the features that come with Android development including SQLite for database interactions, as well as XML for UI design.

## 2 Architectural Design

### 2.1 Component Diagram
![alt text][components]

* The 4 main components that will be working together in this design are:
 * The User component
 * The Grocery List component
 * The Database component
 * The Items component.
* The main interactions are between the Customer and the Grocery List UI whereby the Customer can create lists of their choice, and the GroceryDB searching for the existence of an Item that is being added in the GroceryList.
* The adding of an item is also done by the Customer through the use of the GroceryList UI, however the GroceryList always references the database behind the scenes with every request to add an item.

### 2.2 Deployment Diagram

For the software being developed and with the current design of the system, a Deployment Diagram is not necessary. All of the components will be deployed on the Android device in question, including the SQLite database server to handle Customer requests.  The GroceryList will be accessed through the UI of the system, as well as the Customer's information and the Items that can be added to their list. As such, no diagram should be required with the current status of the system design.

## 3 Low-Level Design

### 3.1 Class Diagram
![alt text][low-level-class]

## 4 User Interface Design
![alt text][UI]
