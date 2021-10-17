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
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;

public class InterpolationSearchProjectVersion2 {

	public static void main(String[] args) {

      /* One important thing I think is to mention that since it's a list of professors, it's already sorted alphabetically by last name, the only extra work our program has to do is sort by first name and department. */
     
      NameArrayList professorList = new NameArrayList();
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
      Scanner in = new Scanner(System.in);
      System.out.println("1. By last name");
      System.out.println("2. By first name");
      System.out.println("3. By department");
      System.out.print("Select how you would like to search: ");
      int selection = in.nextInt();

      switch (selection) {
         case 1:
            System.out.print("Enter the Professor's last name: ");
            String nameToSearch = in.next();
            
            Node[] professorArray = new Node[professorList.size()];
            professorList.toArray(professorArray);
            
            
            Node chosenNode = interpolationSearchByLastName(professorArray, 0, (professorArray.length - 1), nameToSearch); 
            System.out.println("Professor: " + chosenNode.getFirstName() + " " + chosenNode.getLastName() + "\nDepartment: " + chosenNode.getDepartment() + "\nOffice hours: " + chosenNode.getOfficeHourDays() + " at " + chosenNode.getOfficeHours());
            break;
         case 2:
            System.out.print("Enter the Professor's first name: ");
            String firstNameToSearch = in.next();
            Node[] professorArray2 = new Node[professorList.size()];
            professorList.toArray(professorArray2);
      
            Node chosenNode2 = interpolationSearch(professorArray2, 0, (professorArray2.length - 1), firstNameToSearch); 
            System.out.println("Professor: " + chosenNode2.getFirstName() + " " + chosenNode2.getLastName() + "\nDepartment: " + chosenNode2.getDepartment() + "\nOffice hours: " + chosenNode2.getOfficeHourDays() + " at " + chosenNode2.getOfficeHours());
            // System.out.println(chosenNode2.getLastName());
            break;
         default:
            System.out.print("Enter the Department to filter: ");
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
      
      
      
	}

	public static Node interpolationSearch(Node strArray[], int low, int high, String key/*, String OGString*/)
	{
		int position = 0;
		char hiChar = strArray[strArray.length - 1].getFirstName().charAt(0);
		char loChar = strArray[0].getFirstName().charAt(0);
		char keyChar = key.charAt(0);
		int newHigh = 0;
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Probing the position with keeping
	            // uniform distribution in mind.
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
	            newHigh = sumArrayWChar(strArray, key.charAt(0));
               
	            // Condition of target found
	            if (/*strArray[position].charAt(0) == keyChar &&*/ key.equals(strArray[position].getFirstName()))
	                return strArray[position];
	 
	            // If x is larger, x is in right sub array
	            if (strArray[position].getFirstName().charAt(0) <= keyChar && key.length() > 1)
	                return interpolationSearch(strArray, position + 1, position + newHigh, key/*, OGString*/);
	 
	            // If x is smaller, x is in left sub array
	            if (strArray[position].getFirstName().charAt(0) > keyChar && key.length() > 1)
	                return interpolationSearch(strArray, low, position - 1, key/*, OGString*/);
	        }
      }
      catch(StackOverflowError error)
      {
         return strArray[position];
         //InterpolationSearch(strArray, position + 1, high, key, OGString);
      }
		return null;
		
	}

   public static Node interpolationSearchByLastName(Node strArray[], int low, int high, String key/*, String OGString*/)
	{
		int position = 0;
		char hiChar = strArray[strArray.length - 1].getLastName().charAt(0);
		char loChar = strArray[0].getLastName().charAt(0);
		char keyChar = key.charAt(0);
		int newHigh = 0;
      
      try
      {
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Probing the position with keeping
	            // uniform distribution in mind.
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
	            newHigh = sumArrayWCharForLastNames(strArray, key.charAt(0));
               
	            // Condition of target found
	            if (/*strArray[position].charAt(0) == keyChar &&*/ key.equals(strArray[position].getLastName()))
	                return strArray[position];
	 
	            // If x is larger, x is in right sub array
	            if (strArray[position].getFirstName().charAt(0) <= keyChar && key.length() > 1)
	                return interpolationSearchByLastName(strArray, position + 1, position + newHigh, key/*, OGString*/);
	 
	            // If x is smaller, x is in left sub array
	            if (strArray[position].getFirstName().charAt(0) > keyChar && key.length() > 1)
	                return interpolationSearchByLastName(strArray, low, position - 1, key/*, OGString*/);
	        }
      }
      catch(StackOverflowError error)
      {
         return strArray[position];
         //InterpolationSearch(strArray, position + 1, high, key, OGString);
      }
		return null;
		
	}


   public static int sumArrayWChar(Node strArray[], char letter)
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
   }

   public static int sumArrayWCharForLastNames(Node strArray[], char letter)
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
   }
	
}