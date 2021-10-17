import java.util.Comparator;

public class DepartmentNameSort implements Comparator<Node>
{
   public int compare(Node prof1, Node prof2)
   {
      return prof1.getDepartment().compareToIgnoreCase(prof2.getDepartment());
   }
}