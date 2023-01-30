package input_processes;

import java.util.ArrayList;

public class Student {
    private int studentID;
    private ArrayList<Course>enrolledCourses;
    //create constructor
    Student(int studentID) {
        this.studentID = studentID;
        enrolledCourses = new ArrayList<Course>();
    }

    public int getStudentID() {
        return studentID;
    }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void addEnrolledCourse(Course newEnrolledCourse) {
        this.enrolledCourses.add(newEnrolledCourse);
    }
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    public double calculateLinearPenalty() {
        double penalty = 0;
        for(int i = 0; i < enrolledCourses.size()-1; i++) {
            for(int j = i+1; j < enrolledCourses.size(); j++) {
                int gap = Math.abs(enrolledCourses.get(j).getTimeSlot()-enrolledCourses.get(i).getTimeSlot());
                if(gap < 6)
                    penalty += (2 * 5-gap);
            }
        }
        return penalty;
    }

}
