/*
Jaztin Tabunda
Project Partner: Gerardo Marioni 
COMP 282: Project
Assignment:
   Topic/Algorithm: Using Interpolation Search to search CSUN's Faculty/Administration Catalog
   New tentative perspective of plan: Finding a quicker way to search for CSUN Professors
*/

public class InterpolationSearchProjectMain {

	public static void main(String[] args) {
		String[] testStringArray = {"Becky", "Bob", "Brian", "Daniel", "John", "Kaylee", "Nathaniel", "Ryan", "Wesley", "Zion"};
		
		System.out.println(InterpolationSearch(testStringArray, 0, (testStringArray.length - 1), "Kaylee"));
		//InterpolationSearch(testStringArray, 0, (testStringArray.length - 1), "Brian")

	}
	
	public static String InterpolationSearch(String strArray[], int low, int high, String key)
	{
		int position = 0;
		char hiChar = strArray[strArray.length - 1].charAt(0);
		char loChar = strArray[0].charAt(0);
		char keyChar = key.charAt(0);
		
		if (loChar <= hiChar && keyChar >= loChar && keyChar <= hiChar) {
			 
	            // Probing the position with keeping
	            // uniform distribution in mind.
	            position = low + (((high - low) / (hiChar - loChar)) * (keyChar - loChar));
	 
	            // Condition of target found
	            if (strArray[position].charAt(0) == keyChar){
					System.out.println(position);
	                return strArray[position];
	 }
	            // If x is larger, x is in right sub array
	            if (strArray[position].charAt(0) < keyChar)
	                return InterpolationSearch(strArray, position + 1, high, key);
	 
	            // If x is smaller, x is in left sub array
	            if (strArray[position].charAt(0) > keyChar)
	                return InterpolationSearch(strArray, low, position - 1, key);
	        }
		return "not in array";
		
	}
	
}


/*
READ ME:
STILL WORK IN PROGRESS.
9/26/2021 - AS OF RIGHT NOW, I CAN FIND THE NAME THAT MATCHES THE FIRST CHAR. WILL UPDATE, BUT AS OF TODAY,
I ADDED THE BASE CODE/ALGORITHM AND WE CAN FIND THE NAME BASED ON THE FIRST CHAR. - JAZTIN TABUNDA
*/
