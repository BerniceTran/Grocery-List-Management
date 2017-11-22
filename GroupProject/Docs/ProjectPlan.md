# Project Plan

**Author(s):** Nazib Mondal, Ricardo Delahoz, Bernice Tran, Jooyong Park

## 1 Introduction
Our app makes managing grocery lists simple. Our intuitive UI makes it easy for users to add, delete, and check off different items with specific quantities on their list.


## 2 Process Description
**CreateList**

- The CreateList activity stores functions and attributes required for a user to interact with a grocery list. Functions will include createGroceryList, nameList, deleteList, selectList, and saveList. Some attributes required are customerID, listID, userEmail, listName.
- Input: customerID, listModified (boolean)
- Output: listID, listName, listSaved. In general, if the user can create, save, rename, and modify his/her list(s), the activity has completed successfully.
---

**ManageDatabase**

- The ManageDatabase activity stores functions and attributes required to manage and update the database of items while making it searchable. The main functions will include searchItem, getItemName, getItemType, getProduct, and addNewProduct. Any function or attribute that relates the database to its items will be kept in this activity. Furthermore, this activity will also break out different stores and keep current prices.
- Input: storeID, itemID, itemName, itemPrice, itemType, inputName (for search)
- Output: itemMatch (boolean), new and unique itemIDs for new items added to the database, item types attached to each item name. In general, if the grocery list (UI) can search items, fetch items, and add new items to the database, the activity has been completed successfully.
---

**ManageList**

- The ManageList activity stores functions and attributes required to manage and update the grocery list of items. The main functions will include addItem, updateQuantity, deleteItem, checkOffItem, clearChecks, saveChecks, and groupItemsByType. Any function or attribute related to modifying the contents of the list, and not the list itself, will be kept in this activity. This activity contains the core functions that a user will need to manipulate his/her list.
- Input: groceryListID, listName, product, quantity
- Output: listModified (boolean), dateAdded, quantity (new). In general, if a user can change quantities as well as add, delete, and check-off items from his/her grocery list, the activity has been completed successfully.  



## 3 Team
Team Members - Nazib Mondal, Ricardo Delahoz, Bernice Tran, Jooyong Park

**Nazib Mondal** -  Architecture Designer / Software Engineer  - Nazib will oversee overall architectural design of the app and make sure design diagrams are properly written. Nazib will also contribute to programming the app.

**Ricardo Delahoz** - Project Manager / Software Engineer  - Ricardo will submit the final assignment and make sure all checkpoints are being hit along the way to the final submission. He will also assign roles and deadlines as necessary. Ricardo will also contribute to programming the app.

**Bernice Tran** - Head of QA and Testing / Software Engineer - Bernice will oversee all app testing which includes designing all tests, writing them, and recording the outcomes. Bernice will also contribute to programming the app.

**Jooyong Park** - Documenter / UI Designer / Software Engineer - Jooyong will look over and edit all submitted documents to make sure they match the clients' criteria and are easily understandable. Jooyong will also head up UI design and contribute to programming the app.


| Name          | S. Engineering           | Proj Mgmt  | Architecture/UI Design        | Testing           | Documentation |
| ------------- |:-------------:| -----:|-----:|-----:|-----:|
| Nazib Mondal | - | - | Yes| Yes| Yes|
| Ricardo Delahoz   |Yes| Yes | Yes| - | -|
| Bernice Tran    |Yes| Yes | - | Yes | -|
| Jooyong Park |Yes| - | Yes| - | Yes|
