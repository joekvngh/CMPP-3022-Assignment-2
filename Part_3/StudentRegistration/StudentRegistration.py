"""
CMPP-3020 Assignment 2 - Variables, Types, Statements and Subprograms
"""
from  AccessStudentList import get_List
from Student import Student

#hard coded admin login values
ADMIN_USERNAME = "admin"
ADMIN_PASSWORD = "password"
#load the student list
studentList = get_List()


#admin validation
def validate_Admin(username,password):
    if username == ADMIN_USERNAME and password == ADMIN_PASSWORD:
        return True
    else:
        return False
#add student record  
def add_Student_Record():
    print("Enter student's first name:")
    firstNameInput = input(" ")
    print("Enter student's last name:")
    lastNameInput = input(" ")
    print("Enter student's date of birth:")
    dobInput = input(" ")
    print("Enter student's gender:")
    genderInput = input(" ")
    print("Enter student's grade point average:")
    gpaInput = input(" ")
    print("Enter student's current semester:")
    semInput = input(" ")
    print("Enter students program name:")
    progNameInput = input(" ")
    print("Enter students current number of courses:")
    courseNumInput = input(" ")

    newStudent = Student(firstNameInput,lastNameInput,dobInput,genderInput,gpaInput,semInput,progNameInput,courseNumInput)
    studentList.append(newStudent)

#modify student information
def modify_Student():
    print("Enter student's first name:")
    firstNameInput = input(" ")
    print("Enter student's last name:")
    lastNameInput = input(" ")
    searchedStudent = studentList.find_Student_by_Name(firstNameInput,lastNameInput)

    print("Enter student's first name:")
    firstNameInputNew = input(" ")
    searchedStudent.data.set_firstName(firstNameInputNew)

    print("Enter student's last name:")
    lastNameInputNew = input(" ")
    searchedStudent.data.set_lastName(lastNameInputNew)

    print("Enter student's date of birth:")
    dobInput = input(" ")
    searchedStudent.data.set_date(dobInput)

    print("Enter student's gender:")
    genderInput = input(" ")
    searchedStudent.data.set_gender(genderInput)

    print("Enter student's grade point average:")
    gpaInput = input(" ")
    searchedStudent.data.set_gpa(gpaInput)

    print("Enter student's current semester:")
    semInput = input(" ")
    searchedStudent.data.set_currentSemester(semInput)

    print("Enter students program name:")
    progNameInput = input(" ")
    searchedStudent.data.set_programName(progNameInput)

    print("Enter students current number of courses:")
    courseNumInput = input(" ")
    searchedStudent.data.set_numberOfCourses(courseNumInput)
    print("user info removed")

#remove student
def remove_Student():
    print("Enter student's first name:")
    firstNameInput = input(" ")
    print("Enter student's last name:")
    lastNameInput = input(" ")
    studentList.delete_Student(firstNameInput,lastNameInput)
    print("student removed")

#admin menu
def admin_Logic():
    print("Enter admin username: ")
    inputAdminUser = input(" ")
    print("Enter admin password: ")
    inputAdminPass = input(" ")
    if validate_Admin(inputAdminUser,inputAdminPass) == True:
        while True:
            print("Welcome: ",inputAdminUser, " Select Action: ")
            print("1. Add student record")
            print("2. Modify student record")
            print("3. Remove student record")
            print("4. View all student records")
            print("5. Exit")
            menuInput = input(" ")
            if menuInput == ("1"):
                add_Student_Record()
            if menuInput == ("2"):
                modify_Student()
            if menuInput == ("3"):
                remove_Student()
            if menuInput == ("4"):
                studentList.view_Students()
            if menuInput == ("5"):
                break
    else:
        print("wrong option entered")
        return
#student menu
def student_logic():
    print("Enter your first name:")
    firstNameInput = input(" ")
    print("Enter your last name:")
    lastNameInput = input(" ")
    searchedStudent = studentList.find_Student_by_Name(firstNameInput,lastNameInput)
    searchedStudent.data.printStudentInfo()

#Beginning menu
while True:
    print("Welcome to the student registration system")
    print("Select user type: 1 = Admin, 2 = Student, 3 = Exit")
    userResponseMenu = input("")
    if userResponseMenu == ("1"):
        admin_Logic()
        break
    if userResponseMenu == ("2"):
        student_logic()
    if userResponseMenu == ("3"):
        break
