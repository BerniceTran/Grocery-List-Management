[components]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/componentDiagram.png "Components Diagram - Nazib Mondal"
[low-level-class]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Team/teamDesign.PNG "Class Diagram - Team Design"
[UI]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/designUI.png "UI Design - Nazib Mondal"
[states]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/UIStates.png?raw=true "UI States v1.2 - Nazib Mondal"
[here]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/UserManual.md
[link]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/diagramPDF/UIStates.pdf
# Design Document

**Author(s)**: **Team 6**

* *Nazib Mondal*
* *Ricardo Delahoz*
* *Bernice Tran*
* *Jooyong "Daniel" Park*

## 1 Design Considerations

[//]: # (~~~This is just a comment - The interaction between the software and the items database -GroceryStoreDatabase- needs to be further defined on a lower level. As it stands we are using an imaginary database and have yet to properly implement the way the database would work. The design itself also does not reflect how the UI would be handled.~~~)

### 1.1 Assumptions v1.2

* ~~The design assumes the items database is working and negligible in its implementation.~~
* The design assumes a database will be developed that contains an exhaustive amount of common entities that a user would likely search for. This is to reduce insertion times for new data that needs to be added into the database.
* There are dependencies between the groceryList, the Items, and the Users tables for our SQLite Database Design, we are
assuming that these dependencies are resolvable and should not hinder the implementation progress of this design.
* It assumes that the database is fully functional and can easily communicate between the software API and the database itself.
* The design also assumes that there is enough budget to handle the implementation and design of a database.
* The design is assumed to work only for the Android platform, but can possibly be used for porting to other platforms in the future - which is still not the team's main focus at the current time.
* The design assumes hardware should not be an issue for the user that is running this app.

### 1.2 Constraints v1.2

* The budget and time constraints imposed for the creation of the design could have caused a flaw somewhere that might have been overlooked.
* As it stands however, the only constraints for the system are the platform to which the design is to be serving -which is the Android platform- and the implementation of the database that will be used with the software itself.
* There can be physical constraints depending on the user's device itself, whereby their device may not contain enough storage to handle the app's features in question.

### 1.3 System Environment v1.2

* The hardware components the design is to be implemented on is for devices that are running the Android platform.
* This primarily consists of hardware that belong to the mobile device group, including:

 1. Phones
 2. Tablets
 3. Laptops that might be using an Android emulator

* The software that this design will be working with is primarily Android and the features that come with Android development including SQLite for database interactions, as well as XML for UI design.
* The SQLite database will be used for searching as well as for data persistence when the user shuts off the app.

## 2 Architectural Design

### 2.1 Component Diagram v1.2
![alt text][components]

**The 4 main components that will be working together in this design are:**

 1. The User component
 2. The Grocery List component
 3. The Database component
 4. The Items component.

* During actual usage of the app, the main interactions are between the Customer and the Grocery List UI (whereby the Customer can create lists of their choice), and the GroceryDB searching for the existence of an Item that is being added in the GroceryList.
* The adding of an item is also done by the Customer through the use of the GroceryList UI, however the GroceryList always references the database behind the scenes with every request to search for an add-able item.
* The user can also add items to the DB in the case an item does not existing.
* Each individual list will have access to a local centralized GroceryDB, but will have their own tables to handle Item quantities

### 2.2 Deployment Diagram v1.2

For the software being developed and with the current design of the system, a Deployment Diagram is not necessary. All of the components will be deployed on the Android device in question, including the local SQLite database to handle Customer requests and data persistence. The GroceryList will be accessed through the UI of the system, as well as the Customer's information and the Items that can be added to their list. The save states of each, the GroceryList, the corresponding Items in each list and their quantities will be handled via SQLite's local database to aid in data persistence. As such, no diagram should be required with the current status of the system design.

## 3 Low-Level Design

### 3.1 Class Diagram
**Current UML Design**

This is the current class design for the system to be functional. Any changes will be noted through version number.
![alt text][low-level-class]

## 4 User Interface Design
**Concept UI**

This is the initial concept design for our final product. A full explanation of how to use the app's functions can be found [here].
![alt text][UI]

**Version 1.2 UI with States**

As of GLM v1.2 this is the UI's current design with its corresponding states - the pdf for this diagram can be found at this [link].

*Note - due to scalability issues, it would be best to open the image in a new tab to zoom in.*

![alt text][states]
