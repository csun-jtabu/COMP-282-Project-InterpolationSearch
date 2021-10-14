import java.util.ArrayList;

public class QuickSort {
    /* This class will be for sorting the array by first name */
    /* String tempy = professorList.get(1).getFirstName();
      String tempo = professorList.get(2).getFirstName();
      if(tempy.compareTo(tempo) > 0) {
         System.out.println("yay!");
      } */
      public static ArrayList<Node> filter(ArrayList<Node> input, String desiredDept) {
         ArrayList<Node> toReturn = new ArrayList<Node>();
         for(int x = 0; x < input.size(); x++) {
            if(input.get(x).getDepartment() == desiredDept){
               toReturn.add(input.get(x));
            }
         }
         return toReturn;
      }
}
