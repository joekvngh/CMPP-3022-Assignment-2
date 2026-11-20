/*
 * CMPP-3020 Assignment 2 - Variables, Types, Statements and Subprograms
 */
package studentregistration;

public class Student{
    
    //Variables for student
    String firstName;
    String lastName;
    //String used for date of birth since this program does not sort by or manipulate date variable
    String dob;
    char gender;
    //GPA uses decimals float used to handle decimal presicion
    float gpa;
    //Byte used for current semester since there are only 4 choices
    byte currentSemester;
    String programName;
    //Byte used for number of courses since its unlikely student will have more than 127 courses
    byte numberOfCourses;
    
    //Constructor for student
    public Student(String firstName, String lastName, String date, char gender, float gpa, byte currentSemester, String programName, byte nuberOfCourses ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = date;
        this.gender = gender;
        this.gpa = gpa;
        this.currentSemester = currentSemester;
        this.programName = programName;
        this.numberOfCourses = nuberOfCourses;
        
    }
    
    //Variable getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public byte getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(byte currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public byte getNumberOfCourses() {
        return numberOfCourses;
    }

    public void setNumberOfCourses(byte numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }
    
    //Print out student information
    public void printStudentInformation(){
        System.out.println("Name:"+firstName+" "+lastName);
        System.out.println("Date of Birth:"+dob);
        System.out.println("Gender:"+gender);
        System.out.println("GPA:"+gpa);
        System.out.println("Current Semester:"+currentSemester);
        System.out.println("Program Name:"+programName);
        System.out.println("Number of Courses:"+numberOfCourses);
    }
    
}
