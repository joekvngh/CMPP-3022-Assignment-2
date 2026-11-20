/*
 * CMPP-3020 Assignment 2 - Variables, Types, Statements and Subprograms
 */
package studentregistration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ImportStudentList {
    String fileName;
    BufferedReader textFile;
    
    public ImportStudentList(String fileName){
        this.fileName = fileName;
    }    
    
    public LinkedList<Student> readFile(LinkedList<Student> studentList){
        System.out.println(System.getProperty("user.dir"));
        try{
            FileReader reader = new FileReader(fileName);
            textFile = new BufferedReader(reader);
        }catch(IOException ioE){
            System.out.println("No file found, check file path and start program again.");
            System.exit(-1);
        }catch(Exception e){
            System.out.println("Error reading file.");
            System.exit(-1);
        }
        //Attempt to read the text file line by line, each line is one student
        String lineFromFile = "";
        try{
            lineFromFile = textFile.readLine();
        }catch(IOException ioE){
            System.out.println("Error, could extract information from file.");
            System.exit(-1);
        }
        
        while(lineFromFile != null)
        {
            String[] nextValue = lineFromFile.split(",");
            if(nextValue.length < 6){
                System.out.println("Error, issue reading a line from the file, please check for empty lines.");
                System.exit(-1);
            }
            String importFirstName = nextValue[0];
            String importLastName = nextValue[1];
            String importDob = nextValue[2];
            char importGender = '-';
            try{
                importGender = nextValue[3].charAt(0);
            }catch(IndexOutOfBoundsException e){
                System.out.println("Error, issue reading gender data for "+importFirstName+" "+importLastName);
                System.exit(-1);
            }
            float importGpa = 0.0f;
            try{
                importGpa = Float.parseFloat(nextValue[4]);
            }catch(NumberFormatException e){
                System.out.println("Error, issue reading grade point average data for "+importFirstName+" "+importLastName);
                System.exit(-1);
            }
            byte importCurrentSemester = '_';
            try{
                importCurrentSemester = Byte.parseByte(nextValue[5]);
            }catch(NumberFormatException e){
                System.out.println("Error, issue reading current semester data for "+importFirstName+" "+importLastName);
                System.exit(-1);
            }
            String importProgramName = nextValue[6];
            byte importNumberOfCourses = '_';
            try{
                importNumberOfCourses = Byte.parseByte(nextValue[7]);
            }catch(NumberFormatException e){
                System.out.println("Error, issue reading number of courses data for "+importFirstName+" "+importLastName);
                System.exit(-1);
            }
            Student importStudent = new Student(importFirstName, importLastName, importDob, importGender, importGpa, importCurrentSemester, importProgramName, importNumberOfCourses);
            studentList.add(importStudent);
            try{
                lineFromFile = textFile.readLine();
            }catch(IOException ioE){
            System.out.println("Error, could extract information from file.");
            System.exit(-1);
            } 
        }
        
        //Close file before returning updated linked list
        try{
            textFile.close();
        }catch(IOException ioE){
            System.out.println("Error closing file.");
            System.exit(-1);
        }
        
        return studentList;           
    }
}
