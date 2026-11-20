"""
CMPP-3020 Assignment 2 - Variables, Types, Statements and Subprograms
"""

from Student import Student

"""
In this file, external tools such as W3Schools.com and google ai overview were utilized for the completion of linked lists
Explanations of each part is provided below
"""
class Node:
    def __init__(self, data):
        self.data = data  
        self.next = None  

class LinkedList:
    # initialzies the linked list
    def __init__(self):
        self.head = None

    #Adds a node at the end of the list
    #checks if the list is empty and then loops through the list to find the last node.
    def append(self, obj):
        new_node = Node(obj)
        if not self.head:
            self.head = new_node
            return
        
        current = self.head
        while current.next:
            current = current.next
        current.next = new_node

    #Loops through the entire linked list and display their object information through a method defined in Student.py
    def view_Students(self):
        current = self.head
        while current is not None:
            print(current.data.printStudentInfo())
            current = current.next

    #Loops through the linked list to find match to the paramaters
    def find_Student_by_Name(self, first_name,last_name):
        current = self.head
        while current:
            if current.data.firstName == first_name and current.data.lastName == last_name:
                return current
            current = current.next
        return None
    #Same idea as function above but more complicated
    #checks for muiltiple cases 
    def delete_Student(self, first_name, last_name):
            current = self.head

            #checks if the node exists
            if not current:
                return False

            #checks if the node searched for is the head
            if current.data.firstName == first_name and current.data.lastName == last_name:
                self.head = current.next
                return True 

            #checks if the node is somewhere else
            prev = None
            while current:
                if current.data.firstName == first_name and current.data.lastName == last_name:
                    prev.next = current.next 
                    return True  
                
                prev = current
                current = current.next
            return False  

def get_List():
    studentList = LinkedList()
    with open("StudentList.txt", "r") as file:
        for line in file:

            if not line.strip():
                continue
            firstName,lastName,date,gender,gpa,currentSemester,programName,numberOfCourses =  line.strip().split(",")
            student = Student(firstName,lastName,date,gender,gpa,currentSemester,programName,numberOfCourses)
            studentList.append(student)
            
    return studentList



