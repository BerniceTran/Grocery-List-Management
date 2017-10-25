1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).  
Created two objects, a user and customer, to simulate a person using the application. Also created a grocery list object the will create a list and handle the given user command of adding, deleting, and changing quantity of the desired items. 
2. The application must contain a database (DB) of items and corresponding item types. 
Created GroceryStore class for the database that stores items.
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item. 
Used AddedItem class to assist in receiving input from the user and retrieving the item from GroceryStore. The user can then specify what the quantity is in the grocery list itself. Furthermore, made the Item class generalize the type and name of the product.
4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB. 
Created SearchByName class that will receive user input and assign a false value to its attribute match if it cannot be found. The addNewItem operation found in GroceryStore will then add the new item to the database.
5. Lists must be saved automatically and immediately after they are modified.
SaveList class was created in relation to a user and a grocery list.
 6. Users must be able to check off items in a list (without deleting them).
GroceryList was given a checkItem operation in its class
7. Users must also be able to clear all the check-off marks in a list at once. 
Added a ClearChecks operation to GroceryList
8. Check-off marks for a list are persistent and must also be saved immediately. 
*See number 5
9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles). 
Created a GroupByType association class.
10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists. 
Gave the user operations createList, nameList, selectList, and deleteList.
11. The User Interface (UI) must be intuitive and responsive.
Not implemented in this example