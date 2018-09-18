/* ////////////////////////////////////////
 * //NAME: AMAL ABDULRAHAMAN AHEMD       //
 * //COURSE NUMBER: CPCS204              //
 * //SECTION:GBR                         //
 * //TITLE: KAU GRADE BOOK WITH FILE I/O //
 * //DATE SEPTEMBER 15TH, 2018           //
 */////////////////////////////////////////
package kaugradebookio;

/**
 *
 * @author hopes
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class KAUgradebookIO {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Open file and check if it exists or not
        File inputFile = new File("input.txt");
        if (!inputFile.exists()) {
            System.out.println("this file does not exist");
            System.exit(0);
        }

        //Create Scanner for input file
        Scanner input = new Scanner(inputFile); //create PrintWriter to print on the output file.
        PrintWriter output = new PrintWriter("output.txt");
        output.print("Welcome to the KAU Grade Book.");

        //start reading file and store data:
            String courseName = input.next();
            String instructorFirstName = input.next();
            String instructorLastName = input.next();
            int maxNumStudents = input.nextInt();
            
        //Array of object Student
            Student[] students = new Student[maxNumStudents];
        while (input.hasNext()) {

            switch (input.next()) {
                case "ADDRECORD": {
                    addRecord(input, output, students);
                }

                case "SEARCHBYID": {
                }
                case "SEARCHBYNAME": {
                }
                case "DISPLAYSTATS": {
                }
                case "QUIT": {
                    output.print("Thank you for using the KAU Grade Book."
                            + "/nGoodBye.");
                    

                }

            }

        }
        input.close();
        output.close();
    }

    public static void addRecord(Scanner input, PrintWriter output, Student[] students) {
        
//1 . Scan from file all required information
        int id = Integer.parseInt(input.next());
        String f = input.next();
        String l = input.next();
        int one = Integer.parseInt(input.next());
        int two = Integer.parseInt(input.next());
        int three = Integer.parseInt(input.next());
//2 . calculate and save the final grade of student:
        double fGrade = (one * 0.3) + (two * 0.3) + (three * 0.4);

//3 . determine the lettetr grade
char letterGrade = getLetterGrade(fGrade);        

//4 . Save all information into a new Student object
        Student newStudent = new Student(id, f, l, fGrade, letterGrade);

//5 . shifting and insertion
//*Referred from:https://www.geeksforgeeks.org/search-insert-and-delete-in-a-sorted-array/
        boolean shiftToRight = false;
        int index = 0;
        for ( int i = 0; i < Student.getNumStudents(); i++) {
            if (id < students[i].getiD()) {
                shiftToRight = true;
                for (int j = Student.getNumStudents()- 1; j >= i; j--) {
                    students[j + 1] = students[j];//shift forward

                }
                students[i] = newStudent;//inserting in the right place 
                index = i;
                break;
            }

        }
        if (!shiftToRight) {//theninsert normally to the last index of the array
            students[Student.getNumStudents()] = newStudent;

        }
        //increase the number of students
        Student.increaseStudents();

        //print the info on the output file:
        output.println("Command: ADDRECORD\n"
                + students[index].getfName() + " " + students[index].getlName() + " (ID# " + students[index].getiD() + " )"
                + " has been added to the KAU Grade Book.\n \t his final grade is " + students[index].getFinalGrade() + ""
                + " ( " + students[index].getLetterGrade() + ")");

    }
    public static void searchStudentsByID(Scanner input, PrintWriter output, Student[] students){
    //1 . scan from file ID of the student to search for
        int id = Integer.parseInt(input.next());
        int search = -1;
    
    //2 . search for this student ID using binary search:
            
             search = Arrays.binarySearch(students, id);
        
        
    //3 . if the student id is not found, an error message will print
        if(search==0){
        output.println("ERROR: there is no record for student ID # "+id+".");
        }
      
    //4. If the student ID is found, the student’s record will print
        if(search>1){
        output.println("Command: SEARCHBYID");
        output.println("Student Record for Faisal Ashihiri (ID # 111):\n" +
                     "     Exam 1:       "+students[search].getExamGrades(0)+"\n" +
                     "     Exam 2:       "+students[search].getExamGrades(1)+"\n" +
                     "     Final Exam:   "+students[search].getExamGrades(2)+"\n" +
                     "     Final Grade:  "+students[search].getFinalGrade()+"\n" +
                     "     Letter Grade: "+students[search].getLetterGrade());
        }
    
    }
    
    public static void searchStudentsByName(Scanner input, PrintWriter output, Student[] students){
    //1. Scan from file the first and last name of the student to search for.
        String fName = input.next();
        String lName = input.next();
        
    //2. Search for this student using first and last name(linear search)
         int index = 0;
         for (int i = 0; i < Student.getNumStudents()-1; i++) {
             String fSearch = students[i].getfName();
             String lSearch = students[i].getlName();
             if((fSearch.equalsIgnoreCase(fName))&&(lSearch.equalsIgnoreCase(lName))){
                 index = i;
                 break;
             }
            
        }
    //3. if the student is not found, and error message will print.
         output.println("ERROR: there is no record for student \""+fName+" "+lName+"\".");
         
    //4. if the student is found, the student record will print.
         output.println("Command: SEARCHBYNAME");
         output.println("Student Record for Faisal Ashihiri (ID # 111):\n" +
                     "     Exam 1:       "+students[index].getExamGrades(0)+"\n" +
                     "     Exam 2:       "+students[index].getExamGrades(1)+"\n" +
                     "     Final Exam:   "+students[index].getExamGrades(2)+"\n" +
                     "     Final Grade:  "+students[index].getFinalGrade()+"\n" +
                     "     Letter Grade: "+students[index].getLetterGrade());
    }
    
    public static void displayStatistics(Scanner input, PrintWriter output, Student[] students){
    //1. display statistical results for the class
    }
    
    public static void displayAllStudenets(Scanner input, PrintWriter output, Student[] students){
    //1. display the record of all students along with their grade
    //2. if there is no student, an error message should print
    //3. else all students should be printed
    
    }
    
    public static char getLetterGrade(double fGrade){
    //1. determin the letter grade of the student from their final, numerical grade(Standard Scale) then return letter Grade
        if (fGrade > +90) {
            return 'A';
        } else if ((fGrade < 90) && (fGrade >= 80)) {
            return 'B';
        } else if ((fGrade < 80) && (fGrade >= 70)) {
            return 'C';
        } else if ((fGrade < 70) && (fGrade >= 60)) {
           return 'D';
        } else {
            return 'F';
        }
        
    }
    
}
