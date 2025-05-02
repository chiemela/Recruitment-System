import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class RecruitmentSystem extends JFrame implements ActionListener {

    // ======== data structure =================================================
    private final ArrayList<StaffHire> staffList = new ArrayList<>();

    // ======== GUI components (west panel – the input fields) =================
    private final JTextField tfVacancy = new JTextField(10);
    private final JTextField tfDesignation = new JTextField(10);
    private final JTextField tfJobType = new JTextField(10);
    private final JTextField tfStaffName = new JTextField(10);
    private final JTextField tfJoinDate = new JTextField(10);
    private final JTextField tfQualification = new JTextField(10);
    private final JTextField tfAppointedBy = new JTextField(10);
    private final JTextField tfSalary = new JTextField(10);
    private final JTextField tfWeeklyHours = new JTextField(10);
    private final JTextField tfWorkingHour = new JTextField(10);
    private final JTextField tfWagesPerHour = new JTextField(10);
    private final JTextField tfShifts = new JTextField(10);
    private final JTextField tfDisplayNumber = new JTextField(10);   // also “Terminate #”
    private final JCheckBox cbJoined = new JCheckBox("Joined");

    // ======== Buttons ========================================================
    private final JButton btnAddFullTime   = new JButton("Add Full Time Staff");
    private final JButton btnAddPartTime   = new JButton("Add Part Time Staff");
    private final JButton btnSetSalary     = new JButton("Set Salary  (FT)");
    private final JButton btnSetShift      = new JButton("Set Working Shift (PT)");
    private final JButton btnTerminate     = new JButton("Terminate PT");
    private final JButton btnDisplay       = new JButton("Display Number");
    private final JButton btnClear         = new JButton("Clear Fields");

    // ======== Output area ====================================================
    private final JTextArea taOutput = new JTextArea(12, 40);

    // ------------------------------------------------------------------------
    public RecruitmentSystem() {
        super("Recruitment System");
        buildGUI();
        setSize(830, 450);
        setLocationRelativeTo(null);    // centre on screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ------------------------------------------------------------------------
    private void buildGUI() {

        JPanel west = new JPanel(new GridLayout(14, 2, 4, 4));
        west.setBorder(BorderFactory.createTitledBorder("Input Fields"));

        // helper to add a pair
        west.add(new JLabel("Vacancy Number:"));        west.add(tfVacancy);
        west.add(new JLabel("Designation:"));           west.add(tfDesignation);
        west.add(new JLabel("Job Type:"));              west.add(tfJobType);
        west.add(new JLabel("Staff Name:"));            west.add(tfStaffName);
        west.add(new JLabel("Joining Date:"));          west.add(tfJoinDate);
        west.add(new JLabel("Qualification:"));         west.add(tfQualification);
        west.add(new JLabel("Appointed By:"));          west.add(tfAppointedBy);
        west.add(new JLabel("Salary (FT):"));           west.add(tfSalary);
        west.add(new JLabel("Weekly Hours (FT):"));     west.add(tfWeeklyHours);
        west.add(new JLabel("Working Hour (PT):"));     west.add(tfWorkingHour);
        west.add(new JLabel("Wages Per Hour (PT):"));   west.add(tfWagesPerHour);
        west.add(new JLabel("Shifts (PT):"));           west.add(tfShifts);
        west.add(new JLabel("Display / Vacancy #:"));   west.add(tfDisplayNumber);
        west.add(new JLabel("Joined?"));        west.add(cbJoined);

        // east panel – the buttons
        JPanel east = new JPanel(new GridLayout(7, 1, 6, 6));
        east.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton[] btns = { btnAddFullTime, btnAddPartTime, btnSetSalary,
                           btnSetShift, btnTerminate, btnDisplay, btnClear };
        for (JButton b : btns) {
            b.addActionListener(this);
            east.add(b);
        }

        // output area
        taOutput.setEditable(false);
        JScrollPane sp = new JScrollPane(taOutput);

        // layout: BorderLayout
        add(west,  BorderLayout.WEST);
        add(east,  BorderLayout.EAST);
        add(sp,    BorderLayout.SOUTH);
    }

    // ------------------------------------------------------------------------
    //                      Action Handling
    // ------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        try {
            if (src == btnAddFullTime) {
                addFullTime();
            } else if (src == btnAddPartTime) {
                addPartTime();
            } else if (src == btnSetSalary) {
                setSalary();
            } else if (src == btnSetShift) {
                setShift();
            } else if (src == btnTerminate) {
                terminatePartTime();
            } else if (src == btnDisplay) {
                displayStaff();
            } else if (src == btnClear) {
                clearFields();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Number format error: "
                    + nfe.getMessage(), "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ========================================================================
    //                          Button methods
    // ========================================================================
    private void addFullTime() {
        // mandatory integer / double conversions
        int    vacNo  = Integer.parseInt(tfVacancy.getText().trim());
        double salary = Double.parseDouble(tfSalary.getText().trim());
        int    weekly = Integer.parseInt(tfWeeklyHours.getText().trim());

        // check duplicate vacancy number
        for (StaffHire sh : staffList)
            if (sh.getVacancyNumber() == vacNo) {
                JOptionPane.showMessageDialog(this,
                        "Vacancy number already used!", "Duplicate",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        FullTimeStaffHire ft = new FullTimeStaffHire(
                vacNo,
                tfDesignation.getText().trim(),
                tfJobType.getText().trim(),
                tfStaffName.getText().trim(),
                tfJoinDate.getText().trim(),
                tfQualification.getText().trim(),
                tfAppointedBy.getText().trim(),
                cbJoined.isSelected(),
                salary,
                weekly);

        staffList.add(ft);
        JOptionPane.showMessageDialog(this, "Full-time staff added.");
    }

    // ------------------------------------------------------------------------
    private void addPartTime() {
        int    vacNo   = Integer.parseInt(tfVacancy.getText().trim());
        int    workHr  = Integer.parseInt(tfWorkingHour.getText().trim());
        double wageHr  = Double.parseDouble(tfWagesPerHour.getText().trim());

        for (StaffHire sh : staffList)
            if (sh.getVacancyNumber() == vacNo) {
                JOptionPane.showMessageDialog(this,
                        "Vacancy number already used!", "Duplicate",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        PartTimeStaffHire pt = new PartTimeStaffHire(
                vacNo,
                tfDesignation.getText().trim(),
                tfJobType.getText().trim(),
                tfStaffName.getText().trim(),
                tfJoinDate.getText().trim(),
                tfQualification.getText().trim(),
                tfAppointedBy.getText().trim(),
                cbJoined.isSelected(),
                workHr,
                wageHr,
                tfShifts.getText().trim());

        staffList.add(pt);
        JOptionPane.showMessageDialog(this, "Part-time staff added.");
    }

    // ------------------------------------------------------------------------
    private void setSalary() {
        int vacNo = Integer.parseInt(tfVacancy.getText().trim());
        double newSalary = Double.parseDouble(tfSalary.getText().trim());

        for (StaffHire sh : staffList) {
            if (sh.getVacancyNumber() == vacNo && sh instanceof FullTimeStaffHire) {
                FullTimeStaffHire ft = (FullTimeStaffHire) sh;
                ft.setSalary(newSalary);
                JOptionPane.showMessageDialog(this, "Salary updated.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this,
                "No Full-Time staff found with vacancy # " + vacNo,
                "Not Found", JOptionPane.WARNING_MESSAGE);
    }

    // ------------------------------------------------------------------------
    private void setShift() {
        int vacNo = Integer.parseInt(tfVacancy.getText().trim());
        String newShift = tfShifts.getText().trim();

        for (StaffHire sh : staffList) {
            if (sh.getVacancyNumber() == vacNo && sh instanceof PartTimeStaffHire) {
                PartTimeStaffHire pt = (PartTimeStaffHire) sh;
                pt.setShifts(newShift);
                JOptionPane.showMessageDialog(this, "Shift updated.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this,
                "No Part-Time staff found with vacancy # " + vacNo,
                "Not Found", JOptionPane.WARNING_MESSAGE);
    }

    // ------------------------------------------------------------------------
    private void terminatePartTime() {
        int vacNo = Integer.parseInt(tfVacancy.getText().trim());

        for (StaffHire sh : staffList) {
            if (sh.getVacancyNumber() == vacNo && sh instanceof PartTimeStaffHire) {
                PartTimeStaffHire pt = (PartTimeStaffHire) sh;
                pt.terminateStaff();
                JOptionPane.showMessageDialog(this, "Part-time staff terminated.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this,
                "No Part-Time staff found with vacancy # " + vacNo,
                "Not Found", JOptionPane.WARNING_MESSAGE);
    }

    // ------------------------------------------------------------------------
    private void displayStaff() {
        int index = getDisplayIndex();
        if (index == -1) { return; }               // error already reported

        StaffHire sh = staffList.get(index);

        // send the textual information into the output area
        taOutput.append("--- Staff [" + index + "] -------------------------\n");
        sh.display();                              // prints to console
        // we also show here in textarea (quick way: call toString)
        taOutput.append(staffToString(sh) + "\n");
    }

    // converts a StaffHire hierarchy object into one readable block
    private String staffToString(StaffHire sh) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        sh.display();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    // ------------------------------------------------------------------------
    private void clearFields() {
        JTextField[] tfs = { tfVacancy, tfDesignation, tfJobType, tfStaffName,
                             tfJoinDate, tfQualification, tfAppointedBy,
                             tfSalary, tfWeeklyHours, tfWorkingHour,
                             tfWagesPerHour, tfShifts, tfDisplayNumber };

        for (JTextField tf : tfs) tf.setText("");
    }

    // ========================================================================
    //                      Helper: display index with validation
    // ========================================================================
    private int getDisplayIndex() {
        int index = -1;
        try {
            index = Integer.parseInt(tfDisplayNumber.getText().trim());

            if (index < 0 || index >= staffList.size()) {
                JOptionPane.showMessageDialog(this,
                        "Display number must be between 0 and "
                                + (staffList.size() - 1),
                        "Range Error", JOptionPane.ERROR_MESSAGE);
                index = -1;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                    "Display number must be an integer!",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }

    // ========================================================================
    //                               MAIN
    // ========================================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecruitmentSystem::new);
    }
}