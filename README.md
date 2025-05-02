# Recruitment System

A Java-based GUI application for managing staff recruitment.

## Overview

The Recruitment System is a desktop application built using Java and Swing, providing a user-friendly interface for managing staff recruitment. The system allows users to add, update, and terminate both full-time and part-time staff members, storing their details in a list.

## Features

* Add full-time and part-time staff members with relevant details (vacancy number, designation, job type, staff name, joining date, qualification, appointed by, salary/weekly hours, etc.)
* Update salary for full-time staff and working shifts for part-time staff
* Terminate staff members (both full-time and part-time)
* Display staff information by index
* Clear input fields for new entries

## Technical Details

* Built using Java 8 (or later) and Swing for the GUI
* Uses an `ArrayList` to store `StaffHire` objects (both `FullTimeStaffHire` and `PartTimeStaffHire`)
* Implements input validation for numeric fields and display index
* Utilizes a checkbox for "Joined" status

## How to Run

1. Clone the repository to your local machine.
2. Compile the Java files using `javac` or your preferred IDE (e.g., BlueJ).
3. Run the `RecruitmentSystem` class to launch the GUI application.

## Requirements

* Java 8 (or later)
* Swing library (included in the JDK)

## Future Enhancements

* Data persistence (e.g., saving staff data to a file or database)
* Advanced search and filtering capabilities
* User authentication and authorization

## Contributing

Contributions are welcome! If you'd like to improve the Recruitment System, feel free to fork the repository and submit a pull request.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
