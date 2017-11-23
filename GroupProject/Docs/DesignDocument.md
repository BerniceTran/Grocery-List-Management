[components]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/componentDiagram.png "Components Diagram - Nazib Mondal"
[low-level-class]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/UMLDesign.png "Class Diagram v1.5"
[UI]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/designUI.png "UI Design - Nazib Mondal"
[states]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/UIStates.png "UI States v1.2 - Nazib Mondal"
[here]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/UserManual.md
[link]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/diagramPDF/UIStates.pdf
[pdf]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/diagramPDF/testMatrix.pdf
# Design Document

**Author(s)**: **Team 6**

- *Nazib Mondal*
- *Ricardo Delahoz*
- *Bernice Tran*
- *Jooyong "Daniel" Park*

## 1 Design Considerations

### 1.1 Assumptions v1.6+

* ~~The design assumes the items database is working and negligible in its implementation.~~
* The design assumes a database will be developed that contains an exhaustive amount of common entities that a user would likely search for. This is to reduce insertion times for new data that needs to be added into the database.
* There are dependencies between the GroceryList, the Items, and the Users tables for our SQLite Database Design, we are
assuming that these dependencies are resolvable and should not hinder the implementation progress of this design.
* It assumes that the database is fully functional and can easily communicate between the software API and the database itself.
* The design also assumes that there is enough budget to handle the implementation and design of a database.
* The design is assumed to work only for the Android platform, but can possibly be used for porting to other platforms in the future - which is still not the team's main focus at the current time.
* The design assumes hardware should not be an issue for the user that is running this app.
* With the current data structures implemented to handle searching, insertion, and deletion the amount of data that can be handled is assumed to be for private use (eg: not creating a list of a million items, or a million lists of a million items); it has not been tested to work for large sets of data as of yet.
---

### 1.2 Constraints v1.6+

* The budget and time constraints imposed for the creation of the design could have caused a flaw somewhere that might have been overlooked.
* As it stands however, the only constraints for the system are the platform to which the design is to be serving -which is the Android platform- and the implementation of the database that will be used with the software itself.
* There can be physical constraints depending on the user's device itself, whereby their device may not contain enough storage to handle the app's features in question.
* The data structures involved - which in the current implementation should be able to handle requests quite quickly - have not been tested for large-scale use; as such, they constrain how efficiently the app will run.
---
Due to resource constraints (in the form of funds and a narrow set of timeframes) the following features were left unimplemented as of v1.6 of the GLM App:

1. The ability for users to pick and add items to a list through a hierarchical list as stated within the third requirement of the client's specifications. The team deprioritized it for the overarching search function, whereby users can still search the database for items they would like to add - they simply cannot view the entire database at once. This functionality can be built, tested, and ready for release at a later period given more funding.
2. The functionality for the app to recommend the user's to a closely matched item from the database as specified in the fourth requirement of the client's specifications is also set for a future release date. This was deprioritized as well in favor of the overarching search function, which allows users to find items in the database if there are no spelling errors (including case sensitivity) with the item they want, and the item in the database. For this release, we are relying on the user to avoid typos; by the next deliverable this suggestion feature will be built and tested given more funding for this project.
3.  The persistence of check-off marks for each item in a list as specified in the eighth requirement of the client's specifications as our team was unable to debug our checkBox listeners within Android Studio. We could not extract the item name with its corresponding check box, and as such we could not update the database with this information.
4. The requirement of grouping the added items by type as denoted in the client's specifications at point nine was deprioritized in favor of building a working SQLite database. The developed database tracks items by their name and their type, but cannot handle sorting as our team was not able to implement sorting with the given time frame and funding.
5. Automated test cases were not thoroughly checked and written due to time constraint from the learning curve of the system as a whole.

Along with these unimplemented features, the lack of funding with such a narrow timeframe allowed for many bugs to make it through to this release of the app as many test cases had yet to pass. Some of these known bugs are:
1.  The speed of the app significantly slows down upon opening a newly created list - the cause of this is having the SQLite database initialize and run over 100+ insert queries at the time of creation. This is to allow users a wider variety of items to add to their list by having them pre-exist within the database. Given more funding and time, it would be possible to set up an impost to bring the table into SQL through the usage of a CSV file rather than running 100+ separate queries.
2.  The "update quantity" feature also does not do as its name suggests - as it stands, it simply shows the values 1 upon adding an item, and then the value of 2 when exiting the list; any change in value does not actually change the value. Lack of funding has made it difficult for our software engineers to debug this problem.
3. Renaming a list will delete the contents of the original list, and thus in essence, create a new list as opposed to simply renaming a list. Our engineers believe there should be a simple enough fix for this, however due to time constraints they are unable to test and fix this bug.
4. Deleting an item does not immediately delete the item unless you back out of the main menu; once you re-enter the list, the delete method calls and reacts instantly. However, even so, under many circumstances it can be noted that the delete method does not behave as intended. For example, when deleting an item at the bottom of the list, it may delete the very first item as opposed to the intended item; sometimes the delete method can cause the app to crash as well.

* Along with the above there are many more bugs and failed test cases that can be more thoroughly looked over from the app's test matrix [pdf].
* A large part of our resources went to learning the technologies to have the database communicate with the user interface - and along with this, utilizing the database correctly for data persistence and data integrity. Given a wider time frame and more funding many of the above features could be completed in upcoming releases with much higher quality now that the foundations for the app have been developed.

---

### 1.3 System Environment v1.5+

* The hardware components the design is to be implemented on is for devices that are running the Android platform.
* This primarily consists of hardware that belong to the mobile device group, including:

 1. Phones
 2. Tablets
 3. Laptops that might be using an Android emulator

* The software that this design will be working with is primarily Android and the features that come with Android development including SQLite for database interactions, as well as XML for UI design.
* The SQLite database will be used for searching as well as for data persistence when the user shuts off the app.

## 2 Architectural Design

### 2.1 Component Diagram v1.5+
![alt text][components]

**The 5 main components that will be working together in this design are:**

 1. The User component
 2. The Grocery List component
 3. The List Database component
 4. The Items component
 5. The Items Database component

* During actual usage of the app, the main interactions are between the Customer and the Grocery List UI (whereby the Customer can create lists of their choice from the main menu), and the Items Database being searched for addable items to be added to the grocery list.
* The adding of an item is also done by the Customer through the use of the Grocery List UI, however the Grocery List always references the List Database behind the scenes for data persistence, and the Items Database with every request to search for an add-able item.
* The user can also add Items to the Items Database in the case an Item does not exist.
* Each individual Grocery List be stored on the List Database, and will have access to a local centralized Items Database, but will have their own tables to handle Item quantities and persistence.
* The primary use for the List Database component is to help with data persistence; the Items Database component falls under a similar category, but is to keep track of all available Items that can be added to a list. This includes any newly added Items that were not initially available.
---

### 2.2 Deployment Diagram v1.5+

For the software being developed and with the current design of the system, a Deployment Diagram is not necessary. All of the components will be deployed on the Android device in question, including the local SQLite database to handle Customer requests and data persistence. The GroceryList will be accessed through the UI of the system, as well as the Customer's information and the Items that can be added to their list. The save states of each, the GroceryList, the corresponding Items in each list and their quantities will be handled via SQLite's local database to aid in data persistence. The available Items themselves will also persist in the Items Database as new Items are added; as such, no diagram should be required with the current status of the system design as it is too simple and revolves around built-in services of the provided mobile OS architecture (android).

## 3 Low-Level Design

### 3.1 Class Diagram
**Current UML Design v1.5+**

This is the current UML class diagram for the system to be functional. Any changes will be noted through version number.
![alt text][low-level-class]

## 4 User Interface Design
**Concept UI**

This is the initial concept design for our final product. A full explanation of how to use the app's functions can be found [here].
![alt text][UI]
---

**Version 1.6 UI with States**

As of GLM v1.6 (our released product), this is the UI's current design with its corresponding states - the pdf for this diagram can be found at this [link].

*Note - due to scalability issues, it would be best to open the image in a new tab and zooming in.*

![alt text][states]
