public class FullTimeStaffHire extends StaffHire {

    private double salary;
    private int weeklyFractionalHours;

    public FullTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, boolean joined, double salary, int weeklyFractionalHours) {
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined); // Call super constructor with joined status
        this.salary = salary;
        this.weeklyFractionalHours = weeklyFractionalHours;
    }
    
    

    // Accessor methods for salary and weeklyFractionalHours
    public double getSalary() {
        return salary;
    }

    public int getWeeklyFractionalHours() {
        return weeklyFractionalHours;
    }


    public void setSalary(double newSalary) {
        if (isJoined()) {  // Using the correct boolean getter method
            this.salary = newSalary;
        } else {
            System.out.println("No staff appointed to set the salary.");
        }
    }

    public void setWeeklyFractionalHours(int newWeeklyFractionalHours) {
        this.weeklyFractionalHours = newWeeklyFractionalHours;
    }

    @Override // Annotation indicating method overriding
    public void display() {
        super.display(); // Call the display method of the superclass (StaffHire)
        if (isJoined()) { // Check if staff has joined
            System.out.println("Salary: " + salary);
            System.out.println("Weekly Fractional Hours: " + weeklyFractionalHours);
        }
    }
}