[use]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/UseCaseDiagram.png
# Use Case Model

**Author**: Nazib Mondal, Ricardo Delahoz, Bernice Tran, Jooyong Park

## 1 Use Case Diagram
*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*
![use]
## 2 Use Case Descriptions

**Create List**
- Requirements: The customer must be able to create a list and have access to the other helper functions from the creation of the list.
- Pre-conditions: There must be available memory in the device to alow for the creation of the list.
- Post-conditions: An empty list with a a customer given name must be created.
- Scenarios: The customer selects the option to create a new list and is then prompted to name the list. If the customer is satisfied with the list they can save it.

**Name List**
- Requirements: The customer must be able to name a new list or rename an existing list.
- Pre-conditions: There must be a list being created or a list selected by the customer.
- Post-conditions: The list must have a valid name with valid characters.
- Scenarios: After the customer selects or creates list the customer is given a text field where the customer will be giving a name to the list.

**Save List**
- Requirements: The list must be saved after any changes happen to it.
- Pre-condition: There must be a list selected and being edited.
- Post-conditions: A list must be saved to a database where all the lists are being stored.
- Scenarios: The customer will have already either selected o created a list. Then, after a name is chosen for the list it will be saved for either later use or for book keeping purposes. Lastly, during the time the customer is editing the list - i.e. adding, removing items, checking, etc..

**Select List**
- Requirements: The customer must be able to select a list.
- Pre-condition: There must be a list(s) already created. 
- Post-condition: The customer must be on the desired list ready to make any changes.
- Scenarios: After opening the app the customer will simply choose between creating a new list or choosing a preexisting list (if there are any).

**Delete List**
- Requirements: The customer must be able to completely delete a list along with all its contents.
- Pre-conditions: The customer must have a list(s) selected
- Post-conditions: The list and its contents are deleted entirely from the application.
- Scenarios: After the customer has a chosen the list, they are given the option to delete the list.

**Search Item**
- Requirements: The customer must be able to search for an item or group of items by names or type.
- Pre-conditions: There must be a selected inside of a list. In addition, a database of grocery items with valid names and types must be available.
- *Post-conditions: The customer is presented with an item based off searching with a name or a group of items based on a search with item type.
- *Scenarios: Customer will be given an option to search for the grocery item by name or if the item is not found within the database then the grocery store will prompt the user for its type.

**Add Item**
- Requirements: The customer must be able to add an item to the grocery list.
- Pre-conditions: The item previoulsy searched for must be in the grocery store database and the customer must also have a list selected.
- Post-conditions: The grocery list has had an item added to it and the customer is then prompted specify quantity of the the item.
- Scenarios: After the previously searched for item is found in the database the user will be given the option to add the item to the list. The user will then be prompted to update the quantity of the newly added item if they choose to do so.

**Update Item Quantity**
- Requirements: The customer must be able to change the given amount of any added item in the list
- Pre-conditions: There must be an item in the given grocery list.
- Post-conditions: The item quantity of the has been changed to suit the needs of the customer.
- Scenarios: After a chosen item has been added to the list the user can immediately, or at a later time, change the quantity of the desire item to any positive integer.

**Add New Item**
- Requirements: The grocery store must be able to added any new item to its database.
- Pre-conditions: There must be a database and the user must have enter the name of an item not found in the original database contents.
- Post-conditions: There is a new item added to the database.
- Scenarios: After the user enters the name of an item not found in the database the user is met with prompt that asks the user to enter the type of the item. Afterwards, the grocery store will add the newest entry to the database.

**Delete Item**
- Requirements: The user must be able to remove any item from the grocery list.
- Pre-conditions: There must be an item in the selected grocery list.
- Post-conditions: The customer has deleted the undesired item from the list.
- Scenarios: After having added items to a grocery list the user will have the opetion to delete an item.

**Check Items**
- Requirements: The user must be able add or reemove check marks from the list.
- Pre-conditions: There must be an item in the selected grocery list.
- Post-conditions: The item in the list either has a checkmark or has had a checkmark removed from the list
- Scenarios: After having added items to the list the user is presented with boxes next to each one where they will choose to either check or check off any item.
