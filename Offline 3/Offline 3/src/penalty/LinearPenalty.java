package penalty;

import input_processes.Course;
import input_processes.Student;

import java.util.ArrayList;

public class LinearPenalty extends Penalty {
    public double calculateStudentPenalty(Student student) {
        double penalty = 0;
        ArrayList<Course> enrolledCourses = student.getEnrolledCourses();
        for(int i = 0; i < enrolledCourses.size()-1; i++) {
            for(int j = i+1; j < enrolledCourses.size(); j++) {
                int gap = Math.abs(enrolledCourses.get(j).getTimeSlot()-enrolledCourses.get(i).getTimeSlot());
                if(gap < 6)
                    penalty += (2 * (5-gap));
            }
        }
        return penalty;
    }

    @Override
    public double calculateAveragePenalty(ArrayList<Student> students) {
        return super.calculateAveragePenalty(students);
    }
}
