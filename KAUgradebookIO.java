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
    public static int aCount = 0;
    public static int bCount = 0;
    public static int cCount = 0;
    public static int dCount = 0;
    public static int fCount = 0;
          

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
    
    public static void displayStatistics(Scanner input, PrintWriter output, Student[] students, String course, String f, String l){
    //1. display statistical results for the class
        output.println("Command: DISPLAYSTATS");
        output.println("Statistical Results of "+ course+" (Instructor: "+f+" "+ l+"):");
        output.println("\tTotal number of student records: "+Student.getNumStudents());
        //calculate average score traversingly for all students
        double sum =0;        
        for (int i = 0; i < students.length; i++) {
            sum = students[i].getFinalGrade() + sum;  
        }       
        double average = sum/Student.getNumStudents();
        output.println("\tAverage Score: "+average);
    
        //calculate highest and lowest score(max method)
        double max = 0;
        double min = 0;
        for (int i = 0; i < Student.getNumStudents(); i++) {
           max = Math.max(max, students[i].getFinalGrade());
           min = Math.min(min, students[i].getFinalGrade());
        }
        output.println("\tHighest Score: "+max);
        output.println("\tLowest Score: "+min);
        
        //count the a's, b's, c's, d's, and F's..
        output.println("\tTotal 'A' Grades: "+aCount+" ( "+(aCount*Student.getNumStudents()/100)+" % of class)");
        output.println("\tTotal 'B' Grades: "+bCount+" ( "+(bCount*Student.getNumStudents()/100)+" % of class)");
        output.println("\tTotal 'C' Grades: "+cCount+" ( "+(cCount*Student.getNumStudents()/100)+" % of class)");
        output.println("\tTotal 'D' Grades: "+dCount+" ( "+(dCount*Student.getNumStudents()/100)+" % of class)");
        output.println("\tTotal 'F' Grades: "+fCount+" ( "+(fCount*Student.getNumStudents()/100)+" % of class)");
        
    }
    public static void displayAllStudenets(Scanner input, PrintWriter output, Student[] students){
    //1. display the record of all students along with their grade
        output.println("Command: DISPLAYSTUDENTS");
        output.println("***Class Roster and Grade Sheet***");   
        
    //2. if there is no student, an error message should print
        if(Student.getNumStudents()==0)
            output.println("ERROR: There are no students in the system");
        
    //3. else all students informations should be printed
        else{
            for (int i = 0; i < Student.getNumStudents(); i++) {
                output.println("- Student Record for "+students[i].getfName()+" "+students[i].getlName()+" (ID # "+students[i].getiD()+") :");
                output.println("\tExam 1:\t"+students[i].getExamGrades(0));
                output.println("\tExam 2:\t"+students[i].getExamGrades(1));
                output.println("\tFinal Exam:\t"+students[i].getExamGrades(2));
                output.println("\tFinal Grade:\t"+students[i].getFinalGrade());
                output.println("\tFinal Grade:\t"+students[i].getLetterGrade());
            }//end of for loop
        
        }
    
    }
    
    public static char getLetterGrade(double fGrade){
    //1. determin the letter grade of the student from their final, numerical grade(Standard Scale) then return letter Grade
        if (fGrade > +90) {
            aCount++;
            return 'A';
            
        } else if ((fGrade < 90) && (fGrade >= 80)) {
            bCount++;
            return 'B';
            
        } else if ((fGrade < 80) && (fGrade >= 70)) {
            cCount++;
            return 'C';
            
        } else if ((fGrade < 70) && (fGrade >= 60)) {
           dCount++;
           return 'D';
           
        } else {
            fCount++;
            return 'F';
        }
        
    }
    
}
