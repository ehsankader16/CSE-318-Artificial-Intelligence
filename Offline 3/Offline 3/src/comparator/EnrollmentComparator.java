package comparator;

import input_processes.Course;
import java.util.Comparator;

public class EnrollmentComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o2.getTotalEnrollment() - o1.getTotalEnrollment();
    }
}
