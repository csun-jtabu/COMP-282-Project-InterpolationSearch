import java.util.ArrayList;
import java.util.Comparator;

public class QuickSort /*implements Comparator<Node>*/{
    /* This class will be for sorting the array by first name */
    
      public static ArrayList<Node> filter(ArrayList<Node> input, String desiredDept) {
         ArrayList<Node> toReturn = new ArrayList<Node>();
         
         for(int x = 0; x < input.size(); x++) {
            if(input.get(x).getDepartment().contains(desiredDept)){
               toReturn.add(input.get(x));     
            }
         }
         return toReturn;
      }
     
}
