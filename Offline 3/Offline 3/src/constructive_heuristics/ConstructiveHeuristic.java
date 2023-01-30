package constructive_heuristics;

import input_processes.Course;

import java.util.ArrayList;
import java.util.HashSet;

public class ConstructiveHeuristic {
    public int schedule(ArrayList<Course> courses) {
        int courseCount = courses.size();
        int slotCount = 0;
        boolean[] slotUsed = new boolean[courseCount];
        for(int i = 0; i < courseCount; i++) {
            HashSet<Course> conflictingCourses = courses.get(i).getConflictingCourses();
            for(Course conflict: conflictingCourses) {
                if(conflict.getTimeSlot() != -1) {
                    slotUsed[conflict.getTimeSlot()] = true;
                }
            }
            int conflictCount = courses.get(i).getConflictCount();
            for(int j = 0; j <= conflictCount; j++) {
                if(!slotUsed[j]) {
                    if(j == slotCount) {
                        slotCount++;
                    }
                    courses.get(i).setTimeSlot(j);
                    break;
                }
            }
            for(Course conflict: conflictingCourses) {
                if(conflict.getTimeSlot() != -1) {
                    slotUsed[conflict.getTimeSlot()] = false;
                }
            }
        }

        return slotCount;
    }
}
