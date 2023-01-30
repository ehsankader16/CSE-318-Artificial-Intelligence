package constructive_heuristics;

import input_processes.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomOrderingHeuristic {
    public int schedule(ArrayList<Course> courses) {
        Collections.shuffle(courses, new Random(67));
        ConstructiveHeuristic constructiveHeuristic = new ConstructiveHeuristic();
        return constructiveHeuristic.schedule(courses);
    }
}
