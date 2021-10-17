import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
public class QuickSortTest {
    @Test
    public void filterTest() {
        ArrayList<Node> test = new ArrayList<Node>();
        Node toTest = new Node("John", "Smith", "Mo", "0400-1200", "Mathematics");
        test.add(toTest);
        test.add(new Node("a","b","mo","3-4","Life"));
        // assertEquals("Mathematics", QuickSort.filter(test, "Mathematics"));
        System.out.println(test.get(0).getDepartment());
        System.out.println(test.get(1).getDepartment());
        assertEquals("Mathematics", QuickSort.filter(test, "Mathematics"));
    }
    @Test
    public void filterTest2() {
        ArrayList<Node> test = new ArrayList<Node>();
        ArrayList<Node> returned = new ArrayList<Node>();

        Node toTest = new Node("John", "Smith", "Mo", "0400-1200", "Mathematics");
        test.add(toTest);
        test.add(new Node("a","b","mo","3-4","Life"));
        test.add(new Node("a","b","mo","3-4","science"));
        test.add(new Node("a123","b","mo","3-4","Life"));
        returned.add(new Node("a","b","mo","3-4","Life"));
        returned.add(new Node("a","c","mo","3-4","Life"));
        // assertEquals("Mathematics", QuickSort.filter(test, "Mathematics"));
        assertEquals(returned.size(), QuickSort.filter(test, "Life"));
    }
}
