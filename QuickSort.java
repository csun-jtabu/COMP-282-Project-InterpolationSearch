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
         // toReturn.add(input.get(1));
         // toReturn.add(input.get(2));
         // if(input.get(0).getDepartment() == desiredDept){
         //    toReturn.add(input.get(0));
         // }
         for(int x = 0; x < input.size(); x++) {
            if(input.get(x).getDepartment().contains(desiredDept)){
               toReturn.add(input.get(x));
               // System.out.println("-----" + toReturn.get(x).getDepartment());
            }
         }
         // return input.get(0).getDepartment();
         return toReturn;
      }
}
