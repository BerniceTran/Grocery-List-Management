Jooyong Park
Assignment 5
design-information.md

The application was designed with 2 major classes. The Grocery List class which acts as the interface and the Item Database class which handles the back-end.
The Grocery List class includes just 2 attributes, check marks and modified, both of which are boolean. This class contains multiple functions (verbs) which represent the different actions that can be carried out.
These functions include: autoSaveList, checkOffItems, clearCheckMarks, saveCheckMarks, groupItemsByType, createList, renameList, selectList, deleteList.
The other class is Item Database and this is a class made up of Items. Each Item must have 2 attributes, a name and a type. 
In the Item Database, there are 2 levels of categorization, the item and the item type. 
There are several functions which will make the app much more intuitive and user-friendly: serveItemsOfType, suggestSimilarNamedItems, askUserToAddToList, matchFound, lookForItemInDB, askUserToAddToDb, selectNewItemType, saveNewItemAndType.
The 2 major classes, Grocery List and Item Database are joined by 3 similar relationships. The 3 relationships are: change, add, delete.
The change relationship is used specifically to change the quantity of an item already on the list. It has one attribute, quantity, and one function, changeQuantity.
The delete relationship is used to delete an item off of the list. It has one function, deleteItem.
The add relationship is used to add items to the list. It has one attribute, quantity, and three functions to represent the 3 different ways to add items to a list. 
First, an item can be added by picking from a list, that is function pickFromList.
Second, an item can be added by being typed, that is function typeItemName.
Third, after an item is added, the quantity must be specified, and that is done in function specifyQuantity.

In addition, I made three utility classes to support the app called user, date, and store. Although they don't have specific attributes, they will be referenced in the app.

