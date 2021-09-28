import java.util.ArrayList;
public class NameArrayList {
    ArrayList<Node> professors = new ArrayList<Node>();
    /* Theoritcally, this part would be a loop reading in all the info from a file into the arraylist for searching */
    public void fillArrayList(Node input) {
        professors.add(input);
    }
}
