public class StaffHire {

    private int vacancyNumber;
    private String designation;
    private String jobType;
    private String staffName;
    private String joiningDate;
    private String qualification;
    private String appointedBy;
    private boolean joined;


    // Constructor for FullTimeStaffHire and PartTimeStaffHire classes
    public StaffHire(
        int vacancyNumber,
        String designation,
        String jobType,
        String staffName,
        String joiningDate,
        String qualification,
        String appointedBy,
        boolean joined)
        {
            this.vacancyNumber = vacancyNumber;
            this.designation = designation;
            this.jobType = jobType;
            this.staffName = staffName;
            this.joiningDate = joiningDate;
            this.qualification = qualification;
            this.appointedBy = appointedBy;
            this.joined = joined;
        }


    //Original Constructor
    public StaffHire(
        int vacancyNumber,
        String designation,
        String jobType
        ) 
        {
            this.vacancyNumber = vacancyNumber;
            this.designation = designation;
            this.jobType = jobType;
            this.staffName = ""; // Initialize to empty string
            this.joiningDate = ""; // Initialize to empty string
            this.qualification = ""; // Initialize to empty string
            this.appointedBy = ""; // Initialize to empty string
            this.joined = false; // Initialize to false
        }



    // ... (rest of the StaffHire class code: getters, setters, display() )
        // Getter methods
    public int getVacancyNumber() {  return vacancyNumber; }
    public String getDesignation() { return designation;    }
    public String getJobType() {  return jobType;     }
    public String getStaffName() {  return staffName;   }
    public String getJoiningDate() { return joiningDate;   }
    public String getQualification() { return qualification; }
    public String getAppointedBy() { return appointedBy;    }
    public boolean isJoined() { return joined;       }

    // Setter methods
    public void setStaffName(String staffName) 
    {  this.staffName = staffName; }
    public void setJoiningDate(String joiningDate) 
    { this.joiningDate = joiningDate; }
    public void setQualification(String qualification) 
    { this.qualification = qualification;}
    public void setAppointedBy(String appointedBy) 
    {  this.appointedBy = appointedBy; }
    public void setJoined(boolean joined) 
    {  this.joined = joined; }

    public void display() {
        System.out.println("Vacancy Number: " + vacancyNumber);
        System.out.println("Designation: " + designation);
        System.out.println("Job Type: " + jobType);
        System.out.println("Staff Name: " + staffName);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Qualification: " + qualification);
        System.out.println("Appointed By: " + appointedBy);
        System.out.println("Joined: " + joined);
    }

}