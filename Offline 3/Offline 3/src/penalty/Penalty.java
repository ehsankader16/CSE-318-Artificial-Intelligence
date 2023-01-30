package penalty;

import input_processes.Student;

import java.util.ArrayList;

public abstract class Penalty {
    public abstract double calculateStudentPenalty(Student student);
    public double calculateAveragePenalty(ArrayList<Student> students) {
        double penalty = 0;
        for (Student student : students) {
            penalty += calculateStudentPenalty(student);
        }
        return penalty/students.size();
    }
}
