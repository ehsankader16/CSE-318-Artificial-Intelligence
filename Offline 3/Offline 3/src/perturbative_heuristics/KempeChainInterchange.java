package perturbative_heuristics;

import penalty.Penalty;
import input_processes.*;

import java.util.ArrayList;
import java.util.Random;

public class KempeChainInterchange {

    public void runKempe(Graph graph, Penalty penalty, int maxIterations) {
        ArrayList<Course> courses = graph.getCourses();
        ArrayList<Student> students = graph.getStudents();
        Random random = new Random(67);
        double currentPenalty = penalty.calculateAveragePenalty(students);
        for(int i = 0; i < maxIterations; i++) {
            // Randomly select a course with a fixed seed
            Course randomCourse = courses.get(random.nextInt(courses.size()));
            int currentSlot = randomCourse.getTimeSlot();
            int neighborSlot = currentSlot;
            int size = randomCourse.getConflictingCourses().size();
            if(size == 0) {
                i--;
                continue;
            }

            int randomNeighborIndex = random.nextInt(size);
            int j = 0;
            for(Object obj : randomCourse.getConflictingCourses()) {
                if (j == randomNeighborIndex) {
                    neighborSlot = ((Course) obj).getTimeSlot();
                }
                j++;
            }

            // DFS to find all courses that are in the same connected component
            graph.dfs(randomCourse, neighborSlot);

            for(Course course : courses) {
                if (course.isVisited()) {
                    if(course.getTimeSlot() == currentSlot)
                        course.setTimeSlot(neighborSlot);
                    else
                        course.setTimeSlot(currentSlot);
                }
            }
            double penaltyAfterKempe = penalty.calculateAveragePenalty(students);
            if(currentPenalty < penaltyAfterKempe) {
                for(Course course : courses) {
                    if (course.isVisited()) {
                        if(course.getTimeSlot() == currentSlot)
                            course.setTimeSlot(neighborSlot);
                        else
                            course.setTimeSlot(currentSlot);
                    }
                }
            } else {
                currentPenalty = penaltyAfterKempe;
            }

            for(Course course : courses) {
                if (course.isVisited()) {
                    course.setVisited(false);
                }
            }
        }

    }

}
