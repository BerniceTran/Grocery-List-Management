## Section 1: Individual Designs

[design1]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Individual/DefinitelyNotSona/GroceryListUMLNaz.PNG?raw=true "Grocery List Design 1 - Nazib Mondal"
[design2]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Individual/BerniceTran/GroceryListManager.png "Grocery List Design 2 - Bernice Tran"
[design3]: https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Individual/jdapark/design.PNG "Grocery List Design 3 - Jooyong Park"
[design4]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Individual/delahozric/design.jpg "Grocery List Design 4 - Ricardo Delahoz"
[team-design]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Design-Team/teamDesign.PNG "Team Design"


**Design 1: Nazib Mondal**
	![alt text][design1]

	Pros:
		1. The placement and organization of operations and attributes are well done and concise, with the exception of some (mentioned in cons). As a result, it is clear what each class does. - Ricardo
		2. Very neat, easy to read, and I like the customer class that goes above and beyond the assignment's description. -Daniel
		3. The classes are clear and concise; clearly shows the relationship between each class, like how a Customer can make a GroceryList which consists of Item(s) from a Database. -Bernice
	Cons:
		1. I am not sure if I'm corrected but I do believe some of the operations should have been made into associations class. For instance, I don't think a GroceryList should be saved to itself but instead to each user. Therefore, I think there should be an association class between a User and GroceryList that takes care of that. - Ricardo
		2. How come some of your relationships have 2 conflicting cardinalities? Reading it, I can justify it, but is it allowed? -Daniel
		3. What are the differences between your + and - attributes/functions? -Daniel
		4. There should be a grocery list type since it was explicitly mentioned in the assignment, right? -Daniel
		5. Since there are private variables, there should be getters and setters. -Bernice
---

**Design 2: Bernice Tran**
	![alt text][design2]

	Pros:
		1. Components broken down well; I like the way GroceryItem/Type are related to the database Search class -Naz
		2. Includes possible classes that MIGHT be necessary despite not being mentioned in the spec (such as GLType, and a ListManagement Class) -Naz
		3. Found the generalization of GroceryList helpful, as Monthly and Weekly lists are truly two different kinds of list. - Ricardo
		4. Similar to #2, I like how thought through this design is with GroceryListEntry being a separate class, and a ListManagement class I had never thought of. -Daniel
	Cons:
		1. Not sure how necessary the extra classes are, for example GLType - can that not be an attribute for the GroceryList class? -Naz
		2. I do, however, think that SearchGroceryList should be an association relation rather than a binary one. I also think that if we're going to have GroceryListType be it's own class it should extend GroceryList. - Ricardo
		3. Does GroceryListType have to be its own separate thing considering it only has 1 attribute (name)? Similar thoughts for Grocery Type. - Daniel
		4. Your relationships are missing cardinalities. -Daniel

---
**Design 3: Jooyong "Daniel" Park**
	![alt text][design3]

	Pros:
		1. I really like the way your design is structured in a sort of top -> down style, it makes reading and following the connections easier -Naz
		2. Also like the way features are broken down, similarly to Bernice's where the Monthly/Weekly list features are separate -Naz
		3. Found the generalization of GroceryList helpful, as Monthly and Weekly lists are truly two different kinds of list. - Ricardo
		4. I like how the association classes connected the Grocery List class and the Item Database class. The functions in them are clear. -Bernice
	Cons:
		1. I'm not sure how the user is tied to everything in this design, are we saying the User is not the one with the ability to Change/Add/Delete what's in the List? -Naz
		2. I also think there should be a User class as the Customer is surely an object. This should also grant them access to operation being performed on the list. - Ricardo
		3. I’m not sure if the createList(), renameList(), selectList(), and deleteList() should be in the Grocery List class itself. For example, if an instance of Grocery List is created, should it have the ability to create a new list? -Bernice
		4. Should the more intuitive functions in Item Database like askUserToAddToList() be added to database itself because it is just a collection of items and its corresponding item types. -Bernice
---
**Design 4: Ricardo Delahoz**
	![alt text][design4]

	Pros:
		1. Components are broken down neatly with each class having understandable attributes and methods -Naz
		2. Also takes into account possible things that are not in the specs like GroceryStore (which might also be treated as a con) -Naz
		3. This UML seems the most relevant for an actual Android app. It has basic things I missed like customer email address and every object/table has a primary key. -Daniel
		4. Like #2, it is very detailed like taking into account various things like user and store information, and clearly shows the relationship between classes.
	Cons:
		1. I might be misinterpreting some stuff here, namely the necessity of the GroceryStore class - this wasn't mentioned in the specs, unless we're treating this as the Database. It makes logical sense, but do you guys think this is necessary for the design? -Naz
		2. I'm also not sure about the Address class either, if we take out the GroceryStore class then this would likely go away as well; likewise, is it necessary to have User and Customer as separate classes? What other users would there be? Let's talk about this more later -Naz
		3. Building off of what Naz said, I'm not completely sure about GroceryStore either because it was missing from the assignment. If this is going to be a legit app, we probably do need it and its associated Address class.  -Daniel
		4. Why is the GroupByType broken out on its own? -Daniel
		5. I’m not sure why saveList is a separate association class when the function is already in the Customer class.

---
## Section 2: Team Design

**Completed Design:**
![alt text][team-design]

	Commonalities:
		1. The largest similarity between our finalized team-design as opposed to the individual ones would be the inclusion of 4 core classes for the Customer, the GroceryList, the Database, and the Items that are in the database class
		2. The general interaction, relations, and associations between the above four core classes was also about the same for the individual ones with some differences here and there
	Differences:
		1. The largest difference would be the level of detail and optimization in the team-design
		2. In relation to the above, some of the designs were either too simple and lacking in what was necessary to complete the client's requirements, while some contained too much detail and handled extraneous information
		3. The team-design contains much cleaner method names, relation names, class names that are more readable and self-explanatory
		4. The team-design handles precisely what is asked for in the requirements while maintaining room for growth and modification in the case that features may need to be added or modified
		5. Some of the major differences were refining the extra association classes and including them as methods in their respective classes such as AddItem(), GroupByType(), and SaveList() to make the design more readable and logical
		6. Another major difference was removing and/or reincorporating some classes and methods to generalize the design (to "optimize" the design)
	Other Notes:
		1. Largely, I feel all four of our designs were similar. Are real life UMLs more broken out like Ricardo's or more grouped together like Naz's? -Daniel
		2. We used Ricardo's individual-design as a foundation for the team-design and refined it further through discussion to get our current team-design -Naz


---
## Section 3: Summaries

**Conclusions:**

1. To say the least, I learned how to use markdown language! -Naz
2. When discussing changes, we found it very helpful to go back to the source documents (assignment specs, class notes, videos) to decide how to structure the diagram. One big group discussion involved how to handle addItem as that easily could be interpreted as an association class. We referred to the notes on that one and decided to leave it within the GroceryList class. The other big discussion involved how to handle different list types, such as monthly and weekly lists. We decided that monthly and weekly were arbitrary list types that should not be broken out separately on the diagram. Instead, our diagram makes clear that 1 user can have multiple lists, and each list would have a unique ID and possibly a unique name (if the user wants). Regarding teamwork, even with all of our debates and disagreement, everyone was very professional and we ended up with a UML diagram that we all agreed on.   -Daniel
3. One of the main challenges we faced as a team was deciding the relationship between the classes and whether a requirement should be an operation or a separate association class. We constantly had to refer back to the requirements to make sure it was represented in our diagram, as well as refer back to the videos for reference on how to structure it. After hearing out everyone's opinions and deliberating upon them, we added what we thought was necessary, and removed what we thought was unnecessary. We were constantly revising our diagram as new issues came up. Everyone's input was valuable, and ultimately, we ended up with a unified UML diagram that is clear and intuitive. -Bernice
4. The actual design process seems to make more sense now than when I was working individually - for example, listing out every possible necessity and class and breaking the requirements into extremely small parts is better than just trying to get a nicely optimized diagram from the start.
It is much easier to have a good design by starting large with many objects, classes, and relations - then refining the diagram as a group by removing unnecessary things and rearranging the way certain parts interact with each other.
Furthermore, working in a team setting helped me to understand system design on a better level as my teammates were actively able to spot ways to optimize our design that I, myself, could not see and would not notice had I been working alone. I learned a lot from working with my team and I think that's my biggest takeaway from this assignment. -Naz
5. Oh, and to be honest, I learned doing group discussion is quite difficult; finding a method for communicating and then organizing a period where everyone can get together can be quite hard in the face of a deadline.
Furthermore, in the case of using an online chat to communicate ideas it can be quite slow in comparison to say, doing the group work in-person. Hopefully we can find a better means of discussion in the future to increase productivity rate. -Naz
6. I learned that when having a complex design it becomes harder to come back and revise it when one isn't constantly documenting what each part of the design does. As a result, this leads to a lot of wasted time in trying to get a handle on your original thoughts and intentions for the design when trying to clean up. - Ricardo  
