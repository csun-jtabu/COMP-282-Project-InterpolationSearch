import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class InterpolationSearchProjectVersion2Test {
    @Test
    public void testHuges() {
        ArrayList<Node> professorList = new ArrayList<Node>();
        try {
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
        Node[] professorArray = new Node[professorList.size()];
        professorList.toArray(professorArray);
        assertEquals(9, InterpolationSearchProjectVersion2.interpolationSearchByLastName(professorArray, 0, (professorArray.length-1), "Hughes"));
    }   
}