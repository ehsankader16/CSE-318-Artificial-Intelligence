package constructive_heuristics;

import comparator.DegreeComparator;
import input_processes.Course;

import java.util.ArrayList;
import java.util.Collections;

public class LargestDegreeHeuristic {
    public int schedule(ArrayList<Course> courses) {
        Collections.sort(courses, new DegreeComparator());
        ConstructiveHeuristic constructiveHeuristic = new ConstructiveHeuristic();
        return constructiveHeuristic.schedule(courses);
    }

}
