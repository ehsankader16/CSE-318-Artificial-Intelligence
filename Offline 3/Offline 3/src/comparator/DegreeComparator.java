package comparator;

import input_processes.Course;
import java.util.Comparator;

public class DegreeComparator implements Comparator<Course> {

    @Override
    public int compare(Course o1, Course o2) {
        return o2.getConflictCount() - o1.getConflictCount();
    }
}
