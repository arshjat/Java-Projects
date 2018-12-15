package sample;

import javafx.beans.property.SimpleIntegerProperty;

public class Student {
    private String roll;
    private int marks;
    private int q1;
    private int q2;

    Student(){}
    Student(String roll){
        this.roll=roll;
    }
    Student(String roll,int marks){
        this.roll=roll;
        this.marks=marks;
    }

    public void setMarks(final int marks){
        this.marks=marks;
    }

//    public String toString(){
//        return roll + " " + marks;
//    }

    public String getq1MarksString(){
        return " " + q1 + " ";
    }
    public String getq2MarksString(){
        return " " + q2 + " ";
    }



    public String toString(){
        return "   " + marks;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public int getMarks() {
        return marks;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }
}
