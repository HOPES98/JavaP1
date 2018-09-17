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
        char letterGrade = 'A';
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
        if (fGrade > +90) {
            letterGrade = 'A';
        } else if ((fGrade < 90) && (fGrade >= 80)) {
            letterGrade = 'B';
        } else if ((fGrade < 80) && (fGrade >= 70)) {
            letterGrade = 'C';
        } else if ((fGrade < 70) && (fGrade >= 60)) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }

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
        output.print("Command: ADDRECORD\n"
                + students[index].getfName() + " " + students[index].getlName() + " (ID# " + students[index].getiD() + " )"
                + " has been added to the KAU Grade Book.\n \t his final grade is " + students[index].getFinalGrade() + ""
                + " ( " + students[index].getLetterGrade() + ")");

    }
}
