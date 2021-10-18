import java.util.Comparator;

public class FirstNameSort implements Comparator<Node>
{
   public int compare(Node prof1, Node prof2)
   {
      return prof1.getFirstName().compareToIgnoreCase(prof2.getFirstName());
   }
}