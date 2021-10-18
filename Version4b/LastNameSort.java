import java.util.Comparator;

public class LastNameSort implements Comparator<Node>
{
   public int compare(Node prof1, Node prof2)
   {
      return prof1.getLastName().compareToIgnoreCase(prof2.getLastName());
   }
}