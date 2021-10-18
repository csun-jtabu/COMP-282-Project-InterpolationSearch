/*
Group 1: Jaztin Tabunda, Gerardo Marioni 
COMP 282: Project
Assignment:
   Topic/Algorithm: Using Interpolation Search to search CSUN's Faculty/Administration Catalog
   New tentative perspective of plan: Finding a fast way to search for which CSUN professors through different search criteria to 
                                      quickly determine when they would be available to help students.
*/

import java.util.ArrayList;
import java.util.Scanner; 
import java.util.Stack;
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;

public class InterpolationSearchProjectVersion6 {

	public static void main(String[] args) {
     
      long currentTime; //variables used to calculate time
      long endTime;
      long totalTime;
      
        
      NameArrayList professorList = new NameArrayList();
      int selection = 0;
      Scanner in = new Scanner(System.in);
      
      try
      {
         File data = new File("ProfessorData.txt");
         Scanner dataInsert = new Scanner(data);
         dataInsert.useDelimiter(",|\n"); //",|\n"
         while(dataInsert.hasNext() != false)
         {
            String lastName = dataInsert.next();
            String firstName = dataInsert.next();
            String dayAvailable = dataInsert.next();
            String hoursAvailable = dataInsert.next();
            String department = dataInsert.next();
         
            Node prof = new Node(firstName, lastName, dayAvailable, hoursAvailable, department);

            professorList.add(prof);
         }
         dataInsert.close();
      }
      catch(Exception error) {
         System.out.println("Error reading in data.");
      }
      
      try
     { 
      do
      {

         System.out.println("Search CSUN's Professor Database: ");
         System.out.println(" 1. By last name");
         System.out.println(" 2. By first name (Using Interpolation Search)");
         System.out.println(" 3. By department");
         System.out.println(" 4. By first name (Using Binary Search)");
         System.out.println(" 5. Find all professors in a department");
         System.out.print("Select how you would like to search by entering a number: ");
         selection = in.nextInt();
         
         switch (selection) {
            /* each case follows the same basic format: sort by first or last name, put arraylist into an array and then print out the contents of the found node */
         case 1: //case 1 interpolation searches after sorting by last name
            System.out.print("Enter the Professor's last name: ");
            in.nextLine();
            String nameToSearch = in.nextLine();
            professorList.sort(new LastNameSort());//sorting by last name
            Node[] professorArray = new Node[professorList.size()];
            professorList.toArray(professorArray);//puts arraylist into normal array
            //the resulting node from the interpolation search is stored and the following System.out code prints the results of the found node
            Node chosenNode = interpolationSearchByLastName(professorArray, 0, (professorArray.length - 1), nameToSearch); 
            System.out.println("\nProfessor: " + chosenNode.getFirstName() + " " + chosenNode.getLastName() + "\nDepartment: " + chosenNode.getDepartment() + "\nTimes Available: " + chosenNode.getOfficeHourDays() + " at " + chosenNode.getOfficeHours());
            break;
            
         case 2: //case 2 sorts the data by first name and then uses interpolation search based on user input
            System.out.print("Enter the Professor's first name: ");
            in.nextLine();
            String firstNameToSearch = in.nextLine();
            
            currentTime = System.nanoTime(); //this is here to time the program
            
            professorList.sort(new FirstNameSort()); 
            Node[] professorArray2 = new Node[professorList.size()];
            professorList.toArray(professorArray2); 
            Node chosenNode2 = interpolationSearch(professorArray2, 0, (professorArray2.length - 1), firstNameToSearch);
            System.out.println("\nProfessor: " + chosenNode2.getFirstName() + " " + chosenNode2.getLastName() + "\nDepartment: " + chosenNode2.getDepartment() + "\nTimes Available: " + chosenNode2.getOfficeHourDays() + " at " + chosenNode2.getOfficeHours());            
            
            endTime = System.nanoTime(); //for timing program
            totalTime = endTime - currentTime;
            System.out.println("Program Total Time: " + totalTime);
            
            break;
            
         case 3: //Case 3 searches for all nodes with the same department and pushes them into a stack, from there it displays one by one the search results for the user see retrieve the desired result; it is different from 1 and 2 because multiple nodes can have the same department
            Stack<Node> removedStack = new Stack<>();
            do
            {
            System.out.print("Enter the Department the professor is in: ");
            in.nextLine();
            String DepartmentNameToSearch = in.nextLine();
            professorList.sort(new DepartmentNameSort());
            Node[] professorArray3 = new Node[professorList.size()];
            professorList.toArray(professorArray3);
            Node chosenNode3 = interpolationSearchByDepartment(professorArray3, 0, (professorArray3.length - 1), DepartmentNameToSearch); 
            System.out.println("\nProfessor: " + chosenNode3.getFirstName() + " " + chosenNode3.getLastName() + "\nDepartment: " + chosenNode3.getDepartment() + "\nTimes Available: " + chosenNode3.getOfficeHourDays() + " at " + chosenNode3.getOfficeHours());
            professorList.remove(chosenNode3);
            removedStack.push(chosenNode3);
            
            System.out.println("Wrong Professor? Enter 1 to redo the search. Enter any other number to continue.");
            selection = in.nextInt();
            }
            while(selection == 1);
            while(removedStack.isEmpty() == false)
            {
               professorList.add(removedStack.pop());
            }
            break;
            
          case 4: //searching by first name using BINARY searching, same format as cases 1 and 2
            System.out.print("Enter the Professor's first name: ");
            in.nextLine();
            String firstNameToSearchBinary = in.nextLine(); 
            
            currentTime = System.nanoTime(); //to time program
            professorList.sort(new FirstNameSort());
            Node[] professorArray4 = new Node[professorList.size()];
            professorList.toArray(professorArray4);
            Node chosenNode4 = binarySearchFirstName(professorArray4, firstNameToSearchBinary); 
            System.out.println("Professor: " + chosenNode4.getFirstName() + " " + chosenNode4.getLastName() + "\nDepartment: " + chosenNode4.getDepartment() + "\nTimes Available: " + chosenNode4.getOfficeHourDays() + " at " + chosenNode4.getOfficeHours());
            
            endTime = System.nanoTime(); //to time program
            totalTime = endTime - currentTime;
            System.out.println("Program Total Time: " + totalTime);
            
            break;
            
         default: //the default case (option 5 in the menu) simply searches for all the nodes with the same department and prints them out
            System.out.print("Enter the Department to filter: ");
            String dept = in.next();
            ArrayList<Node> deptArray = QuickSort.filter(professorList, dept);
            System.out.println("Number of results: " + deptArray.size());
            for(int x = 0; x<deptArray.size(); x++) {
               deptArray.get(x).printString();
            }
            in.nextLine();
            break;
      }
      
      System.out.print("\nWould you like to search again?\n"
                        + "1. Yes\n"
                        + "2. No\nSelection: ");
      selection = in.nextInt();
      System.out.println();
      }while(selection == 1);
     }
     catch(ArrayIndexOutOfBoundsException arrError)
     {
        System.out.println("Professor Not Found"); 
     }
     catch(Exception inputError)
     {
        System.out.println("Sorry, Please Enter a Valid Selection. The Program has been terminated.");
     }
      
	}

	public static Node interpolationSearch(Node strArray[], int low, int high, String key) //interpolationSearch for first Name
	{
      Node nullNode = new Node(" ", " ", " ", " ", " ");
	   int position = 0;
		char hiChar = strArray[strArray.length - 1].getFirstName().charAt(0);
		char loChar = strArray[0].getFirstName().charAt(0);
		char keyChar = key.charAt(0);
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
               // Interpolation search formula
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
               
	            // When the key matches the node/professor in the array
	            if (strArray[position].getFirstName().contains(key)) {
	                return strArray[position];
	 }
	            // If the key is is on the upper end of the list when comparing to the element in the array
	            if (strArray[position].getFirstName().charAt(0) <= keyChar)
	                return interpolationSearch(strArray, position + 1, high, key); 
	 
	            // If the key is on the lower end of the list when comparing to the element in the array
	            if (strArray[position].getFirstName().charAt(0) >= keyChar)
	                return interpolationSearch(strArray, low, position - 1, key);
	        }
      }
      catch(StackOverflowError error)
      {
         if(strArray[position].getFirstName().contains(key)) //The program will try to find a close match if it's unable to find the exact name
         {
            return strArray[position];
         }
         return interpolationSearch(strArray, low - 1, position + 1, key); //if the program miscalculates, it'll widen it's search
         
      }
		return nullNode; //returns blank if no node can be found
   }


   public static Node interpolationSearchByLastName(Node strArray[], int low, int high, String key)
	{
      Node nullNode = new Node(" ", " ", " ", " ", " ");
	   int position = 0;
		char hiChar = strArray[strArray.length - 1].getLastName().charAt(0);
		char loChar = strArray[0].getLastName().charAt(0);
		char keyChar = key.charAt(0);
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Interpolation search formula
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
               
	            // When the key matches the node/professor in the array
	            if (key.equals(strArray[position].getLastName()))
	                return strArray[position];
	 
	            // If the key is is on the upper end of the list when comparing to the element in the array
	            if (strArray[position].getLastName().charAt(0) <= keyChar)
	                return interpolationSearchByLastName(strArray, position + 1, high, key); 
	 
	            // If the key is on the lower end of the list when comparing to the element in the array
	            if (strArray[position].getLastName().charAt(0) > keyChar)
	                return interpolationSearchByLastName(strArray, low, position - 1, key);
	        }
      }
      catch(StackOverflowError error)
      {
         if(strArray[position].getLastName().contains(key)) //makes sure to return something similar to the input
         {
            return strArray[position];
         }
         return interpolationSearchByLastName(strArray, low - 1, position + 1, key); //if the program miscalculates, it'll widen it's search
         
      }
		return nullNode; //returns blank if no node can be found
	}
   
   public static Node interpolationSearchByDepartment(Node strArray[], int low, int high, String key)
	{
      Node nullNode = new Node(" ", " ", " ", " ", " ");
	   int position = 0;
		char hiChar = strArray[strArray.length - 1].getDepartment().charAt(0);
		char loChar = strArray[0].getDepartment().charAt(0);
		char keyChar = key.charAt(0);
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
               // Interpolation search formula
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
               
	            // When the key matches the node/professor in the array
	            if (key.equals(strArray[position].getDepartment()))
	                return strArray[position];
	 
	            // If the key is is on the upper end of the list when comparing to the element in the array
	            if (strArray[position].getDepartment().charAt(0) <= keyChar)
	                return interpolationSearchByDepartment(strArray, position + 1, high, key); 
	 
	            // If the key is on the lower end of the list when comparing to the element in the array
	            if (strArray[position].getDepartment().charAt(0) > keyChar)
	                return interpolationSearchByDepartment(strArray, low, position - 1, key);
	        }
      }
      catch(StackOverflowError error)
      {
         if(strArray[position].getDepartment().contains(key))
         {
            return strArray[position];
         }
         return interpolationSearchByDepartment(strArray, low - 1, position + 1, key); //if the program miscalculates, it'll widen it's search
      }
		return nullNode;
	}



  public static Node binarySearchFirstName(Node[] profArray, String wantedName) //binarySearch to compare between Interpolation search and Binary Search
    {
        Node nullNode = new Node(" ", " ", " ", " ", " ");
        int start = 0; //start of index 
        int ending = profArray.length - 1; //end of index
        
        while (start <= ending) {
            int middle = start + (ending - start) / 2;
 
            int check = wantedName.compareTo(profArray[middle].getFirstName());
 
            // Check if key is present at mid
            if (check == 0)
                return profArray[middle];
 
            // If key is greater, ignore left half
            if (check > 0)
                start = middle + 1;
 
            // If key is smaller, ignore right half
            else
                ending = middle - 1;
        }
        
        return nullNode;
    }
	
}