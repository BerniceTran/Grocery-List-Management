[use]:https://github.com/qc-se-fall2017/370Fall17Team6/blob/master/GroupProject/Docs/UseCaseDiagram.png
# Use Case Model

*This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: \<person or team name\>

## 1 Use Case Diagram
*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*
![use]
## 2 Use Case Descriptions

*For each use case in the use case diagram, this section should contain a description, with the following elements:*

- *Requirements: High-level description of what the use case must allow the user to do.*
- *Pre-conditions: Conditions that must be true before the use case is run.*
- *Post-conditions Conditions that must be true once the use case is run.*
- *Scenarios: Sequence of events that characterize the use case. This part may include multiple scenarios, for normal, alternate, and exceptional event sequences. These scenarios may be expressed as a list of steps in natural language or as sequence diagrams.*

**Create List**
- *Requirements: The user must be able to create a list and have access to the other helper functions from the creation of the list.*
- *Pre-conditions: There must be available memory in the device to alow for the creation of the list.*
- *Post-conditions: An empty list with a a user given name must be created.*
- *Scenarios: The user selects the option to create a new list and is then prompted to name the list. If the user is satisfied with the list they can save it.*

**Name List**
- *Requirements: The user must be able to name a new list or rename an existing list.*
- *Pre-conditions: There must be a list being created or a list selected by the user.*
- *Post-conditions: The list must have a valid name with valid characters.*
- *Scenarios: After the user selects or creates list the user is given a text field where the user will be giving a name to the list.*

**Save List**
- *Requirements: The list must be saved after any changes happen to it.*
- *Pre-condition: There must be a list selected and being edited.*
- *Post-conditions: A list must be saved to a database where all the lists are being stored.*
- *Scenarios: The user will have already either selected o created a list. Then, after a name is chosen for the list it will be saved for either later use or for book keeping purposes. Lastly, during the time the user is editing the list - i.e. adding, removing items, checking, etc..*

**Select List**
- *Requirements: The user must be able to select a list.*
- *Pre-condition: There must be a list(s) already created. *
- *Post-condition: The user must be on the desired list ready to make any changes.*
- *Scenarios: After opening the app the user will simply choose between creating a new list or choosing a preexisting list (if there are any).*
