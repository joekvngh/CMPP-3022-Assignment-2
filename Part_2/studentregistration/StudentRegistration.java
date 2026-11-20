/*
 * CMPP-3020 Assignment 2 - Variables, Types, Statements and Subprograms
 */
package studentregistration;

import java.util.LinkedList;
import java.util.Scanner;

public class StudentRegistration {

    //Constants, hardcoded values for admin account credentials
    static final String ADMIN_USERNAME = "admin";
    static final String ADMIN_PASSWORD = "password";
    
    //Main program entry
    public static void main(String[] args) {
        //Initialization, setting variables
        LinkedList<Student> studentList = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        String fileName;
        String response = "";
        boolean userResponse = false;
        
        //When running from source (in netbeans) paste: src/studentregistration/StudentList.txt
        //Enter path to example file
        System.out.println("Enter file path:");
        fileName = input.nextLine();
        
        //Attempt to read file specified by user and create a linked list of students
        ImportStudentList importStudentList = new ImportStudentList(fileName);
        studentList = importStudentList.readFile(studentList);
        
        //Login prompt, first asks if user is admin or student, calls respective subprogram
        do {
            System.out.println("Welcome to the student registration system.");
            System.out.println("Select user type: 1 = Admin, 2 = Student, 3 = Exit");
            response = input.nextLine();
            
            //Using switch to process user input
            switch(response){
            case "1":
                userResponse = adminLogic(studentList, input);
                break;
            case "2":
                userResponse = studentLogic(studentList, input);
                break;
            case "3":
                userResponse = true;
                break;
            default:
                System.out.println("Invalid input, please try again.");
            }
        }while (!userResponse);
        
        System.out.println("Thank you for using the program.");
        //End of program
    }
    
    //Provides intial prompt for admin credentials and gives admin option to add, modify, delete and view student records
    private static boolean adminLogic (LinkedList<Student> studentList, Scanner input){
        String adminUserName;
        String adminPassword;
        System.out.println("Enter Admin username");
        adminUserName = input.nextLine();
        System.out.println("Enter Admin password:");
        adminPassword = input.nextLine();
        //Check if admin username and password entered by user match hardcoded values
        if(validateAdmin(adminUserName,adminPassword) == true){
            boolean shouldExit = false;
            String menuOption = "";
            //Uses do while loop to prompt user for choice and evaluate response
            do {
                System.out.println("Welcome "+adminUserName+". Select action:");
                System.out.println("1. Add student record");
                System.out.println("2. Modify student record");
                System.out.println("3. Remove student record");
                System.out.println("4. View all student records");
                System.out.println("5. Exit");
                menuOption = input.nextLine();
                if(menuOption.equals("1")){
                    studentList = addStudent(studentList, input);
                }
                else if(menuOption.equals("2")){
                    studentList = modifyStudent(studentList, input);
                }
                else if(menuOption.equals("3")){
                    studentList = removeStudent(studentList, input);
                }
                else if(menuOption.equals("4")){
                    viewStudents(studentList);                
                }
                else if(menuOption.equals("5")){
                    shouldExit = true;
                }
                else{
                    System.out.println("Error, invalid option. Please try again.");
                }
            }while(!shouldExit);
            
            return true;
        }
        else{
            System.out.println("Invalid username or password.");
            return false;
        }
    }
    //Subprogram prompts user to enter new student information, creates a student object and adds it to the instance of the linked list, returning updated instance
    private static LinkedList<Student> addStudent(LinkedList<Student> studentList, Scanner input){
        //Declaring varibales to accept user input
        String inputFirstName;
        String inputLastName;
        String inputDob;
        char inputGender;
        float inputGpa;
        byte inputCurrentSemester;
        String inputProgramName;
        byte inputNumberOfCourses;
        int studentIndex = -1;
        
        //Prompting user to enter new student information and parsing into correct variable types
        //If parsing results in error, no student is added, user is notified and returned to admin menu
        System.out.println("#Add New Student#");
        System.out.println("Enter student's first name:");
        inputFirstName = input.nextLine();
        System.out.println("Enter student's last name:");
        inputLastName = input.nextLine();
        
        //Checks if student name already exists, program only handles one student per identical first and last name
        studentIndex = findStudent(studentList, inputFirstName, inputLastName);
        if(studentIndex > -1){
            System.out.println("Student already exists. Failed to add student.");
            return studentList;
        }
        
        System.out.println("Enter student's date of birth:");
        inputDob = input.nextLine();
        System.out.println("Enter student's gender:");
        try{
            inputGender = input.nextLine().charAt(0);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Invalid input, failed to add student.");
            return studentList;
        }
        System.out.println("Enter student's grade point average:");
        try{
            inputGpa = Float.parseFloat(input.nextLine().trim());
        }catch(NumberFormatException e){
            System.out.println("Invalid input, failed to add student.");
            return studentList;
        }
        System.out.println("Enter student's current semester:");
        try{
            inputCurrentSemester = Byte.parseByte(input.nextLine().trim());
        }catch(NumberFormatException e){
            System.out.println("Invalid input, failed to add student.");
            return studentList;
        }
        System.out.println("Enter student's program name:");
        inputProgramName = input.nextLine();
        System.out.println("Enter student's current number of courses:");
        try{
            inputNumberOfCourses= Byte.parseByte(input.nextLine().trim());
        }catch(NumberFormatException e){
            System.out.println("Invalid input, failed to add student.");
            return studentList;
        }    
        Student newStudent = new Student(inputFirstName, inputLastName, inputDob, inputGender, inputGpa, inputCurrentSemester, inputProgramName, inputNumberOfCourses);
        studentList.add(newStudent);
        System.out.println("Student added");
        return studentList;
    }
    
    //Subprogram prompts user to enter student name and if student exists, prompts user to enter new values for that student
    private static LinkedList<Student> modifyStudent(LinkedList<Student> studentList, Scanner input){
        String inputFirstName;
        String inputLastName;
        int studentIndex = -1;
        System.out.println("#Modify Student#");
        System.out.println("Enter student's first name:");
        inputFirstName = input.nextLine();
        System.out.println("Enter student's last name:");
        inputLastName = input.nextLine();
        
        //Checks if student exists, exits to menu if first and last name do not match
        studentIndex = findStudent(studentList, inputFirstName, inputLastName);
        if(studentIndex <= -1){
            System.out.println("Could not find student.");
            return studentList;
        }
        //If match is found, prompt user for updated student fields
        else{
            String inputDob;
            char inputGender;
            float inputGpa;
            byte inputCurrentSemester;
            String inputProgramName;
            byte inputNumberOfCourses;
            System.out.println("Update student's date of birth (currently set to "+studentList.get(studentIndex).getDob()+"):");
            inputDob = input.nextLine();
            System.out.println("Update student's gender (currently set to "+studentList.get(studentIndex).getGender()+"):");
            try{
                inputGender = input.nextLine().charAt(0);
            }catch(IndexOutOfBoundsException e){
                System.out.println("Invalid input, failed to update student.");
                return studentList;
            }
            System.out.println("Update student's grade point average (currently set to "+studentList.get(studentIndex).getGpa()+"):");
            try{
                inputGpa = Float.parseFloat(input.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Invalid input, failed to update student.");
                return studentList;
            }            
            System.out.println("Update student's current semester (currently set to "+studentList.get(studentIndex).getCurrentSemester()+"):");
            try{
                inputCurrentSemester = Byte.parseByte(input.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Invalid input, failed to update student.");
                return studentList;
            }
            System.out.println("Update student's program name (currently set to "+studentList.get(studentIndex).getProgramName()+"):");
            inputProgramName = input.nextLine();
            System.out.println("Update student's current number of courses (currently set to "+studentList.get(studentIndex).getNumberOfCourses()+"):");
            try{
                inputNumberOfCourses= Byte.parseByte(input.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Invalid input, failed to update student.");
                return studentList;
            }
            //Update student object
            studentList.get(studentIndex).setDob(inputDob);
            studentList.get(studentIndex).setGender(inputGender);
            studentList.get(studentIndex).setGpa(inputGpa);
            studentList.get(studentIndex).setCurrentSemester(inputCurrentSemester);
            studentList.get(studentIndex).setProgramName(inputProgramName);
            studentList.get(studentIndex).setNumberOfCourses(inputNumberOfCourses);
            return studentList;
        }
    }
    //Prompts user to enter student name, if student exists, remove from linked list
    private static LinkedList<Student> removeStudent(LinkedList<Student> studentList, Scanner input){
        String inputFirstName;
        String inputLastName;
        int studentIndex = -1;
        System.out.println("#Remove Student#");
        System.out.println("Enter student's first name:");
        inputFirstName = input.nextLine();
        System.out.println("Enter student's last name:");
        inputLastName = input.nextLine();
        studentIndex = findStudent(studentList, inputFirstName, inputLastName);
        if(studentIndex <= -1){
            System.out.println("Could not find student.");
            return studentList;
        }
        else{
            studentList.remove(studentIndex);
            System.out.println("Student removed.");
            return studentList;
        }
    }
    //Calls student print method for each student in the linked list
    private static void viewStudents(LinkedList<Student> studentList){
        System.out.println("#Student List#");
        System.out.println("--------------------------------------");
        for(int i = 0; i < studentList.size(); i++){
            System.out.println("Student #"+ (i+1));
            System.out.println("--------------------------------------");
            studentList.get(i).printStudentInformation();
            System.out.println("--------------------------------------");
        }
    }
    
    //Check if username and password match admin constants
    private static boolean validateAdmin (String userName, String password){
        if(userName.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)){
            return true;
        }
        else{
            return false;           
        }
    }
    
    //Ask for students first and last name, if student record exists, print student information and exit
    private static boolean studentLogic (LinkedList<Student> studentList, Scanner input){
        boolean valideStudentInfo = false;
        int studentIndex = -1;
        String firstNameInput;
        String lastNameInput;
        do {
            System.out.println("Enter your first name:");
            firstNameInput = input.nextLine();
            System.out.println("Enter your last name:");
            lastNameInput = input.nextLine();
            studentIndex = findStudent(studentList, firstNameInput, lastNameInput);
            if(studentIndex >= 0){
                valideStudentInfo = true;
            }
            else{
                System.out.println("Name not found. Please try again.");
            }
        }while(!valideStudentInfo);
        System.out.println("Student #"+ (studentIndex+1));
        System.out.println("--------------------------------------");
        studentList.get(studentIndex).printStudentInformation();
        System.out.println("--------------------------------------");        
        return true;        
    }
    
    //Iterate through linked list and return index of student record
    private static int findStudent (LinkedList<Student> studentList, String firstName, String lastName){
        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getFirstName().toLowerCase().equals(firstName.toLowerCase()) && studentList.get(i).getLastName().toLowerCase().equals(lastName.toLowerCase())){
                return i;
            }
        }
        return -1;
    }
    
}
