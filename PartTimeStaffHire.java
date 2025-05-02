public class PartTimeStaffHire extends StaffHire {

    private int workingHour;
    private double wagesPerHour;
    private String shifts;
    private boolean terminated;

    public PartTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, boolean joined, int workingHour, double wagesPerHour, String shifts) {
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined); // Call super constructor with joined status

        this.workingHour = workingHour;
        this.wagesPerHour = wagesPerHour;
        this.shifts = shifts;
        this.terminated = false;
    }

    // Accessor methods
    public int getWorkingHour() {
        return workingHour;
    }

    public double getWagesPerHour() {
        return wagesPerHour;
    }

    public String getShifts() {
        return shifts;
    }

    public boolean isTerminated() {
        return terminated;
    }


    public void setShifts(String newShifts) {
        if (isJoined()) {
            this.shifts = newShifts;
        }
    }


    public void terminateStaff() {
        if (terminated) { // Use the terminated attribute for the check
            System.out.println("Staff is already terminated.");
        } else {
            setStaffName("");
            setJoiningDate("");
            setQualification("");
            setAppointedBy("");
            setJoined(false);
            this.terminated = true;
        }
    }



    @Override
    public void display() {
        super.display();

        if (!terminated && isJoined()) { // Only display if not terminated and joined
            System.out.println("Wages Per Hour: " + wagesPerHour);
            System.out.println("Working Hour: " + workingHour);
            System.out.println("Shifts: " + shifts);
            System.out.println("Income Per Day: " + (wagesPerHour * workingHour)); // Calculate income
            System.out.println("Terminated: "+ terminated);
        } else if (terminated && isJoined() == false){
            System.out.println("Terminated: "+ terminated);
        }
    }
}