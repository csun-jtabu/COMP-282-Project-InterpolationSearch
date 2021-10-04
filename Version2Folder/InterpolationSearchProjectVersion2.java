//import java.util.ArrayList;
import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;

public class InterpolationSearchProjectVersion2 {

	public static void main(String[] args) {
		
      /*Node test = new Node("John", "Smith", "Tuesdays and Thursdays", "2-3pm", "Computer Science");
      Node test2 = new Node("Kaylee", "Smith", "Tuesdays and Thursdays", "2-3pm", "Computer Science");
      Node test3 = new Node("Krass", "Smith", "Tuesdays and Thursdays", "2-3pm", "Computer Science");
      Node test4 = new Node("Zion", "Smith", "Tuesdays and Thursdays", "2-3pm", "Computer Science");
      professorList.add(test);
      professorList.add(test2);
      professorList.add(test3);
      professorList.add(test4);*/
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
      }
      catch(Exception error)
      {
      }
      
      Node[] professorArray = new Node[professorList.size()];
      professorList.toArray(professorArray);
      
      Node chosenNode = interpolationSearch(professorArray, 0, (professorArray.length - 1), "Bernardo"); //This Interpolation search currently works only with the first name.
      System.out.println(chosenNode.getLastName());
      
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
	
}