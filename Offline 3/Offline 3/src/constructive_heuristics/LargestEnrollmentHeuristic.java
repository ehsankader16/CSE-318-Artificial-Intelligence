package constructive_heuristics;

import comparator.EnrollmentComparator;
import input_processes.Course;

import java.util.ArrayList;
import java.util.Collections;

public class LargestEnrollmentHeuristic {

    public int schedule(ArrayList<Course> courses) {
        Collections.sort(courses, new EnrollmentComparator());
        ConstructiveHeuristic constructiveHeuristic = new ConstructiveHeuristic();
        return constructiveHeuristic.schedule(courses);
    }
}
