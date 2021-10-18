/*
Jaztin Tabunda
Project Partner: Gerardo Marioni 
COMP 282: Project
Assignment:
   Topic/Algorithm: Using Interpolation Search to search CSUN's Faculty/Administration Catalog
   New tentative perspective of plan: Finding a quicker way to search for CSUN Professors
*/
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner; 
import java.util.Stack;
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;

public class InterpolationSearchProjectVersion3 {

	public static void main(String[] args) {

      /* One important thing I think is to mention that since it's a list of professors, it's already sorted alphabetically by last name, the only extra work our program has to do is sort by first name and department. */
     
      long currentTime; //variables used to calculate time
      long endTime;
      long totalTime;
      /*currentTime = System.currentTimeMillis();
      endTime = System.currentTimeMillis();
      totalTime = endTime - currentTime;
      System.out.println(totalTime);*/
        
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
            //System.out.println(department);
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
         //Scanner in = new Scanner(System.in);
         System.out.println("Search CSUN's Professor Database: ");
         System.out.println(" 1. By last name");
         System.out.println(" 2. By first name (Using Interpolation Search)");
         System.out.println(" 3. By department");
         System.out.println(" 4. By first name (Using Binary Search)");
         System.out.println(" 5. Find all professors in a department");
         System.out.println("Select how you would like to search by entering a number: ");
         selection = in.nextInt();
         
         switch (selection) {
         case 1:
            System.out.println("Enter the Professor's last name (Place Underscores for Spaces): ");
            String nameToSearch = in.next();
               
            professorList.sort(new LastNameSort());
            Node[] professorArray = new Node[professorList.size()];
            professorList.toArray(professorArray);
            //System.out.println(professorList);
            Node chosenNode = interpolationSearchByLastName(professorArray, 0, (professorArray.length - 1), nameToSearch); 
            System.out.println("\nProfessor: " + chosenNode.getFirstName() + " " + chosenNode.getLastName() + "\nDepartment: " + chosenNode.getDepartment() + "\nOffice hours: " + chosenNode.getOfficeHourDays() + " at " + chosenNode.getOfficeHours());
            break;
            
         case 2:
            System.out.println("Enter the Professor's first name (Place Underscores for Spaces): ");
            String firstNameToSearch = in.next();
            
            currentTime = System.nanoTime(); //to time program
            
            professorList.sort(new FirstNameSort());
            Node[] professorArray2 = new Node[professorList.size()];
            professorList.toArray(professorArray2);
            
            //System.out.println(professorList);
            Node chosenNode2 = interpolationSearch(professorArray2, 0, (professorArray2.length - 1), firstNameToSearch); 
            //System.out.println(professorArray2[14]);
            System.out.println("Professor: " + chosenNode2.getFirstName() + " " + chosenNode2.getLastName() + "\nDepartment: " + chosenNode2.getDepartment() + "\nOffice hours: " + chosenNode2.getOfficeHourDays() + " at " + chosenNode2.getOfficeHours());
            // System.out.println(chosenNode2.getLastName());
            
            endTime = System.nanoTime();
            totalTime = endTime - currentTime;
            System.out.println("Program Total Time: " + totalTime);
            
            break;
            
         case 3:
            Stack<Node> removedStack = new Stack<>();
            do
            {
            System.out.println("Enter the Department the professor is in (Place Underscores for Spaces): ");
            String DepartmentNameToSearch = in.next();
            
            professorList.sort(new DepartmentNameSort());
            Node[] professorArray3 = new Node[professorList.size()];
            professorList.toArray(professorArray3);
            
            //System.out.println(professorList);
            Node chosenNode3 = interpolationSearchByDepartment(professorArray3, 0, (professorArray3.length - 1), DepartmentNameToSearch); 
            //System.out.println(chosenNode3);
            System.out.println("Professor: " + chosenNode3.getFirstName() + " " + chosenNode3.getLastName() + "\nDepartment: " + chosenNode3.getDepartment() + "\nOffice hours: " + chosenNode3.getOfficeHourDays() + " at " + chosenNode3.getOfficeHours());
            // System.out.println(chosenNode2.getLastName());
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
            
          case 4:
            System.out.println("Enter the Professor's first name (Place Underscores for Spaces): ");
            String firstNameToSearchBinary = in.next();
            
            currentTime = System.nanoTime(); //to time program
            
            professorList.sort(new FirstNameSort());
            Node[] professorArray4 = new Node[professorList.size()];
            professorList.toArray(professorArray4);
            
            //System.out.println(professorList);
            Node chosenNode4 = binarySearchFirstName(professorArray4, firstNameToSearchBinary); 
            //System.out.println(professorArray2[14]);
            System.out.println("Professor: " + chosenNode4.getFirstName() + " " + chosenNode4.getLastName() + "\nDepartment: " + chosenNode4.getDepartment() + "\nOffice hours: " + chosenNode4.getOfficeHourDays() + " at " + chosenNode4.getOfficeHours());
            // System.out.println(chosenNode2.getLastName());
            
            endTime = System.nanoTime();
            totalTime = endTime - currentTime;
            System.out.println("Program Total Time: " + totalTime);
            
            break;
            
         default:
            System.out.println("Enter the Department to filter (Place Underscores for Spaces): ");
            String dept = in.next();
            // Node[] professorArray2 = new Node[professorList.size()];
            // professorList.toArray(professorArray2);
            ArrayList<Node> deptArray = QuickSort.filter(professorList, dept);
            // System.out.println(deptArray.get(0).getDepartment());
            System.out.println("Number of results: " + deptArray.size());
            for(int x = 0; x<deptArray.size(); x++) {
               deptArray.get(x).printString();
            }
            // System.out.println(deptArray.get(1).getDepartment());
            // System.out.println(deptArray.get(0).getFirstName()+ " " + deptArray.get(0).getLastName() + "\n" + deptArray.get(0).getDepartment());
            // Node chosenNode2 = interpolationSearch(professorArray2, 0, (professorArray2.length - 1), firstNameToSearch); //This Interpolation search currently works only with the first name.
            // System.out.println("Professor: " + chosenNode2.getFirstName() + " " + chosenNode2.getLastName() + "\nDepartment: " + chosenNode2.getDepartment() + "\nOffice hours: " + chosenNode2.getOfficeHourDays() + " at " + chosenNode2.getOfficeHours());
            break;
      }
      
      System.out.println("\nWould you like to search again?\n"
                        + "1. Yes\n"
                        + "2. No");
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

	public static Node interpolationSearch(Node strArray[], int low, int high, String key/*, String OGString*/) //interpolationSearch for first Name
	{
      Node nullNode = new Node(" ", " ", " ", " ", " ");
	   int position = 0;
		char hiChar = strArray[strArray.length - 1].getFirstName().charAt(0);
		char loChar = strArray[0].getFirstName().charAt(0);
		char keyChar = key.charAt(0);
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Probing the position with keeping
	            // uniform distribution in mind.
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
               
	            // Condition of target found
	            if (strArray[position].getFirstName().contains(key)) {
	                return strArray[position];
	 }
	            // If x is larger, x is in right sub array
	            if (strArray[position].getFirstName().charAt(0) <= keyChar /*&& key.length() > 1*/)
	                return interpolationSearch(strArray, position + 1, high, key/*, OGString*/); 
	 
	            // If x is smaller, x is in left sub array
	            if (strArray[position].getFirstName().charAt(0) >= keyChar /*&& key.length() > 1*/)
	                return interpolationSearch(strArray, low, position - 1, key/*, OGString*/);
	        }
      }
      catch(StackOverflowError error)
      {
         if(strArray[position].getFirstName().contains(key)) //The program will try to find a close match if it's unable to find the exact name
         {
            return strArray[position];
         }
         return interpolationSearch(strArray, low - 1, position + 1, key/*, OGString*/); //if the program miscalculates, it'll widen it's search
         //InterpolationSearch(strArray, position + 1, high, key, OGString);
      }
		return nullNode;
   }


   public static Node interpolationSearchByLastName(Node strArray[], int low, int high, String key/*, String OGString*/)
	{
      Node nullNode = new Node(" ", " ", " ", " ", " ");
	   int position = 0;
		char hiChar = strArray[strArray.length - 1].getLastName().charAt(0);
		char loChar = strArray[0].getLastName().charAt(0);
		char keyChar = key.charAt(0);
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Probing the position with keeping
	            // uniform distribution in mind.
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
               
	            // Condition of target found
	            if (/*strArray[position].charAt(0) == keyChar &&*/ key.equals(strArray[position].getLastName()))
	                return strArray[position];
	 
	            // If x is larger, x is in right sub array
	            if (strArray[position].getLastName().charAt(0) <= keyChar /*&& key.length() > 1*/)
	                return interpolationSearchByLastName(strArray, position + 1, high, key/*, OGString*/); 
	 
	            // If x is smaller, x is in left sub array
	            if (strArray[position].getLastName().charAt(0) > keyChar /*&& key.length() > 1*/)
	                return interpolationSearchByLastName(strArray, low, position - 1, key/*, OGString*/);
	        }
      }
      catch(StackOverflowError error)
      {
         if(strArray[position].getLastName().contains(key))
         {
            return strArray[position];
         }
         return interpolationSearchByLastName(strArray, low - 1, position + 1, key/*, OGString*/); //if the program miscalculates, it'll widen it's search
         //InterpolationSearch(strArray, position + 1, high, key, OGString);
      }
		return nullNode;
	}
   
   public static Node interpolationSearchByDepartment(Node strArray[], int low, int high, String key/*, String OGString*/)
	{
      Node nullNode = new Node(" ", " ", " ", " ", " ");
	   int position = 0;
		char hiChar = strArray[strArray.length - 1].getDepartment().charAt(0);
		char loChar = strArray[0].getDepartment().charAt(0);
		char keyChar = key.charAt(0);
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Probing the position with keeping
	            // uniform distribution in mind.
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
               
	            // Condition of target found
	            if (/*strArray[position].charAt(0) == keyChar &&*/ key.equals(strArray[position].getDepartment()))
	                return strArray[position];
	 
	            // If x is larger, x is in right sub array
	            if (strArray[position].getDepartment().charAt(0) <= keyChar /*&& key.length() > 1*/)
	                return interpolationSearchByDepartment(strArray, position + 1, high, key/*, OGString*/); 
	 
	            // If x is smaller, x is in left sub array
	            if (strArray[position].getDepartment().charAt(0) > keyChar /*&& key.length() > 1*/)
	                return interpolationSearchByDepartment(strArray, low, position - 1, key/*, OGString*/);
	        }
      }
      catch(StackOverflowError error)
      {
         if(strArray[position].getDepartment().contains(key))
         {
            return strArray[position];
         }
         return interpolationSearchByDepartment(strArray, low - 1, position + 1, key/*, OGString*/); //if the program miscalculates, it'll widen it's search
         //InterpolationSearch(strArray, position + 1, high, key, OGString);
      }
		return nullNode;
	}


   /*public static int sumArrayWChar(Node strArray[], char letter)
   {
      int sum = 0;
      for(Node node : strArray)
      {
         if(letter == node.getFirstName().charAt(0))
         {
            sum++;
         }
      }
      return sum;
   }*/

   /*public static int sumArrayWCharForLastNames(Node strArray[], char letter)
   {
      int sum = 0;
      for(Node node : strArray)
      {
         if(letter == node.getLastName().charAt(0))
         {
            sum++;
         }
      }
      return sum;
   }*/
   
 /*  public boolean checkTime(Node prof)  Work in progress
   {
      String profStartTime = 
      String profEndTime = 
   }
 */
 
  public static Node binarySearchFirstName(Node[] profArray, String wantedName)
    {
        Node nullNode = new Node(" ", " ", " ", " ", " ");
        int start = 0; //l 
        int ending = profArray.length - 1; //r
        
        while (start <= ending) {
            int middle = start + (ending - start) / 2;
 
            int check = wantedName.compareTo(profArray[middle].getFirstName());
 
            // Check if x is present at mid
            if (check == 0)
                return profArray[middle];
 
            // If x greater, ignore left half
            if (check > 0)
                start = middle + 1;
 
            // If x is smaller, ignore right half
            else
                ending = middle - 1;
        }
        
        return nullNode;
    }
	
}