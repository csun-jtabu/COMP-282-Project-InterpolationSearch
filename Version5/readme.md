# COMP-282-Project-InterpolationSearch
COMP 282 Project:

Team 1: Jaztin Tabunda and Gerardo Marioni

Topic/Algorithm: Using Interpolation Search to search CSUN's
Faculty/Administration Catalog

New tentative perspective of plan: Finding a quicker way to search for CSUN Professors


Plans:
- Perfect the interpolation search to return the exact name in the array (Possibly using for loops to traverse array and string characters)
- Gather Data from CSUN's Web Page of Professors and put it on a txt file
- Create a class of Professors and enter in an array (Possible Attributes: Office Hours)
- Make a menu that searches for the Professor by name or by office hours


Quick Tracking of Contributions:
  
  Jaztin:
- Collected 30 Data samples of Professors: first name, last name, days they are available, hours they are available, department
- Made Interpolation Search method which utilizes interpolation search to find the professor based on the first name only.
- Made file containing the data samples.
- Adjusted Interpolation Search to fit with Gerardo's arraylist and node methods
- Made sumArrayWChar method to fit with Interpolation search
- Made a loop to insert the collected data into data fields and inserted into the Node
- Debugged Gerardo's NameArrayList Class file to fit with my interpolation search method 
- Debugged InterpolationSearch for Last name 
- Created the sort classes to sort names by Department, first Name and last name
- Debugged InterpolationSearch for First Name
- Debugged ProfessorData (Added Underscores for Spaces)
- Added a way to widen the interpolation search in case the program miscalculates because of a character
- Made try/catch statements to address ArrayOutOfBounds error and StackOverflow error
- Created toString method for Node
- Created InterpolationSearchByDepartment Method
- Edited the Menu Options
- Addressed Menu Option error when entering a string input instead of an int

  Gerardo:
- Assisted brainstorming for direction of project. 
- Stored incremental progress of program to Github
- Debugged interpolation search algorithm by first and last name to turn up correct results
- Created sorting class to find all nodes that have the same department
- Layed foundation for the menu format of the program
- Created ArrayList and Node classes to store data for searching and sorting
- Debugged program to no longer need underscores in place of spaces
- Created toString method to print out data in Nodes
- Created NameArayList Class
- Implemented Jaztin's interpolation search algorithm to work on last names
- Debugged Interpolation Search for First Names


