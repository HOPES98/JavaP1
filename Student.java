/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaugradebookio;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author hopes
 */
public class Student {//implements Comparator<Student> {
    private String fName;
    private String lName;
    private int iD;
    private int[] examGrades=new int[3];
    private double finalGrade;
    private char letterGrade;
    private static int numStudents =0;
    
    public Student(int iD, String fName, String lName, double finalGrade, char letterGrade){
    this.iD = iD;
    this.fName = fName;
    this.lName = lName;
    this.finalGrade = finalGrade;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
//////////////////////////////////////////////////////
    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
///////////////////////////////////////////////////////
    public int getExamGrades(int index) {
        return examGrades[index];
    }

    public void setExamGrades(int examGrade, int index) {
        
        this.examGrades[index] = examGrade;
    }
///////////////////////////////////////////////////////
    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }
    ///////////////////////////////////////////////////////

    public char getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }
    ///////////////////////////////////////////////////////

    public static int getNumStudents() {
        return numStudents;
    }

    public static void setNumStudents(int numStudents) {
        Student.numStudents = numStudents;
    }
    ///////////////////////////////////////////////////////
    public static void increaseStudents(){
        numStudents++;
    }
//    ///////////////////////////////////////////////////////
//    public static void sortByID(Student [] array){
//    Arrays.sort(array, new Comparator<Student>() {
//        @Override
//        public int compare(Student student1, Student student2) {
//        return student1.iD.compareTo(student2.iD);    
//        }
//    });
//   }
//
//    @Override
//    public int compare(Student o1, Student o2) {
//     return o1.iD.compareTo(o2.iD);    }
//
//    
//    
//    
    
}
