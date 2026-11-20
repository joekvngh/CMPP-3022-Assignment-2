"""
CMPP-3020 Assignment 2 - Variables, Types, Statements and Subprograms
"""

class Student:
    #constructor
    def __init__(self, firstName, lastName, date, gender, gpa, currentSemester, programName, numberOfCourses):
        self.firstName = firstName
        self.lastName = lastName
        self.date = date
        self.gender = gender
        self.gpa = gpa
        self.currentSemester = currentSemester
        self.programName = programName
        self.numberOfCourses = numberOfCourses

    #setters  
    def set_firstName(self,first_name):
        self.FirstName = first_name
    def set_lastName(self,last_name):
        self.lastName = last_name
    def set_date(self,new_date):
        self.date = new_date
    def set_gender(self,new_gender):
        self.gender = new_gender
    def set_gpa(self,new_gpa):
        self.gpa = new_gpa
    def set_currentSemester(self,current_semester):
        self.currentSemester = current_semester
    def set_programName(self,program_name):
        self.programName = program_name
    def set_numberOfCourses(self,number_of_courses):
        self.numberOfCourses = number_of_courses

    #getters
    def get_firstName(self):
        return self.firstName
    def get_lastName(self):
        return self.lastName
    def get_date(self):
        return self.date
    def get_gpa(self):
        return self.gpa
    def get_currentSemester(self):
        return self.currentSemester
    def get_programName(self):
        return self.programName
    def get_numberOfCourses(self):
        return self.numberOfCourses

    #print student info
    def printStudentInfo(self):
        print("Name: ",self.firstName," ", self.lastName)
        print("Date of Birth: ", self.date)
        print("Gender: ", self.gender)
        print("GPA: ",self.gpa)
        print("Current Semester: ", self.currentSemester)
        print("Program Name: ", self.programName)
        print("Number of Courses: ", self.numberOfCourses)
        