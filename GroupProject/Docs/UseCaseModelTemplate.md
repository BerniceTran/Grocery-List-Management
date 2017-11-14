[use]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/designPNG/UseCaseDiagram.png
# Use Case Model

**Author**: Nazib Mondal, Ricardo Delahoz, Bernice Tran, Jooyong Park

## 1 Use Case Diagram

![use]
## 2 Use Case Descriptions

**Create List v1.2**
- Requirements: The customer must be able to create a list and have access to the other helper functions from the creation of the list.
- Pre-conditions: There must be available memory and a working SQLite database implemented to store the list.
- Post-conditions: An empty list with a customer given name must be created, and added to its corresponding table in the SQLite .
- Scenarios: The customer selects the option to create a new list and is then prompted to name the list. If the customer is satisfied with the list they can save it.

**Name List v1.2**
- Requirements: The customer must be able to name a new list or rename an existing list.
- Pre-conditions: There must be a list being created or a list selected by the customer.
- Post-conditions: The list must have a valid name with valid characters, and the corresponding table must be modified in the database.
- Scenarios: After the customer selects or creates list the customer is given a text field where the customer will be giving a name to the list.

**Save List v1.2**
- Requirements: The list must be saved after any changes happen to it.
- Pre-condition: There must be a list selected and being edited either through creation, renaming, or the addition of items and quantity changes from within the list.
- Post-conditions: The list that is modified must have its corresponding information in the database updated.
- Scenarios: The customer will have already either selected o created a list. Then, after a name is chosen for the list it will be saved for either later use or for book keeping purposes. Lastly, during the time the customer is editing the list - i.e. adding, removing items, checking, etc..

**Select List**
- Requirements: The customer must be able to select a list.
- Pre-condition: There must be a list(s) already created.
- Post-condition: The customer must be on the desired list ready to make any changes.
- Scenarios: After opening the app the customer will simply choose between creating a new list or choosing a preexisting list (if there are any).

**Delete List v1.2**
- Requirements: The customer must be able to completely delete a list along with all of its contents.
- Pre-conditions: The customer must have a list(s) selected
- Post-conditions: The list and its contents are deleted entirely from the application, and should be handled accordingly within the database.
- Scenarios: After the customer has a selected the list, they are given the option to delete the list and all of its contents.

**Search Item**
- Requirements: The customer must be able to search for an item or group of items by names or type.
- Pre-conditions: There must be a selected inside of a list. In addition, a database of grocery items with valid names and types must be available.
- Post-conditions: The customer is presented with an item based off searching with a name or a group of items based on a search with item type.
- Scenarios: Customer will be given an option to search for the grocery item by name or if the item is not found within the database then the grocery store will prompt the user for its type.

**Add Item v1.2**
- Requirements: The customer must be able to add an item to the grocery list.
- Pre-conditions: The item previously searched for must be in the grocery store database and the customer must also have a list selected.
- Post-conditions: The grocery list has had an item added to it, and the customer will have the option of updating the quantity of added item.
- Scenarios: After the previously searched for item is found in the database the user will be given the option to add the item to the list. The user will then be prompted to update the quantity of the newly added item if they choose to do so.

**Update Item Quantity v1.2**
- Requirements: The customer must be able to change the given amount of any added item in the list
- Pre-conditions: There must be an item in the given grocery list, and the item should have a corresponding quantity.
- Post-conditions: The item quantity has been changed to suit the needs of the customer.
- Scenarios: After a chosen item has been added to the list the user can immediately, or at a later time, change the quantity of the desire item to any positive integer.

**Add New Item v1.2**
- Requirements: The SQLite database must be able to be add any new item to its database in the case a valid item does not exist already.
- Pre-conditions: There must be a database and the user must have entered the name of an item that could not be found in the original database.
- Post-conditions: There is a new valid item added to the database with a corresponding type.
- Scenarios: After the user enters the name of an item not found in the database the user is met with prompt that asks the user to enter the type of the item. Afterwards, the item will be added to the database.

**Delete Item**
- Requirements: The user must be able to remove any item from their grocery list.
- Pre-conditions: There must be an item in the selected grocery list.
- Post-conditions: The customer has deleted the undesired item from their list, and this modification should save in the corresponding table of the database.
- Scenarios: After having added items to a grocery list the user will have the option to delete an item.

**Check Items**
- Requirements: The user must be able add or remove check marks from the list.
- Pre-conditions: There must be an item in the selected grocery list.
- Post-conditions: The item in the list either has a checkmark or has had a checkmark removed from the list.
- Scenarios: After having added items to the list the user is presented with boxes next to each one where they will choose to either check or check off any item.
