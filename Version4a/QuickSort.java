import java.util.ArrayList;
import java.util.Comparator;

public class QuickSort /*implements Comparator<Node>*/{
    /* This class will be for sorting the array by first name */
    /* String tempy = professorList.get(1).getFirstName();
      String tempo = professorList.get(2).getFirstName();
      if(tempy.compareTo(tempo) > 0) {
         System.out.println("yay!");
      } */
      public static ArrayList<Node> filter(ArrayList<Node> input, String desiredDept) {
         ArrayList<Node> toReturn = new ArrayList<Node>();
         
         for(int x = 0; x < input.size(); x++) {
            if(input.get(x).getDepartment().contains(desiredDept)){
               toReturn.add(input.get(x));     
            }
         }
         return toReturn;
      }
     
     /*@Override  
     public int compare(Node prof1, Node prof2)
     {
      return prof1.getFirstName().compareToIgnoreCase(prof2.getFirstName());
     }*/
}
