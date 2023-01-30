package perturbative_heuristics;

import penalty.*;
import input_processes.*;
import java.util.ArrayList;
import java.util.Random;

public class PairSwapOperator {
    public void runPairSwap(Graph graph, Penalty penalty, int maxIterations) {
        ArrayList<Course> courses = graph.getCourses();
        ArrayList<Student> students = graph.getStudents();
        Random random = new Random(67);
        double currentPenalty = penalty.calculateAveragePenalty(students);
        for (int i = 0; i < maxIterations; i++) {
            Course course1 = courses.get(random.nextInt(courses.size()));
            Course course2 = courses.get(random.nextInt(courses.size()));

            int course1Slot = course1.getTimeSlot();
            int course2Slot = course2.getTimeSlot();
            if(course1Slot == course2Slot) {
                continue;
            }
            for(Course course : course1.getConflictingCourses()) {
                if(course.getTimeSlot() == course2Slot)
                    continue;
            }

            for(Course course : course2.getConflictingCourses()) {
                if(course.getTimeSlot() == course1Slot)
                    continue;
            }

            course1.setTimeSlot(course2Slot);
            course2.setTimeSlot(course1Slot);
            double penaltyAfterPairSwap = penalty.calculateAveragePenalty(students);;
            if(currentPenalty < penaltyAfterPairSwap) {
                course1.setTimeSlot(course1Slot);
                course2.setTimeSlot(course2Slot);
            } else {
                currentPenalty = penaltyAfterPairSwap;
            }

        }
    }
}
