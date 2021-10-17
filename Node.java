public class Node {

    public Node(String fN, String lN, String oHD, String oH, String dept) {
        setFirstName(fN);
        setLastName(lN);
        setOfficeHourDays(oHD);
        setOfficeHours(oH);
        setDepartment(dept);
    }
    /* This class creates the nodes that have the professor's name, office hours, department, and contact info */
    private String firstName;
    private String lastName;
    private String officeHourDays;
    private String officeHours;
    private String department;


    public void printString() {
        System.out.println("Professor " + this.firstName + " " + this.lastName + "\nOffice hour days: " + this.officeHourDays + "\nOffice hours: " + this.officeHours + "\nDepartment: " + this.department + "\n");
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } 
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setOfficeHourDays(String officeHourDays) {
        this.officeHourDays = officeHourDays;
    }
    public String getOfficeHourDays() {
        return officeHourDays;
    }
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }
    public String getOfficeHours() {
        return officeHours;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }
    
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.department + " ";
    }
}
