package constructive_heuristics;

import comparator.DegreeComparator;
import input_processes.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SaturationDegreeHeuristic {
    public int schedule(ArrayList<Course> courses) {
        int courseCount = courses.size();
        int slotCount = 0;
        for(int i = 0; i < courseCount; i++) {
            int maxSaturation = -1;
            int maxNode = -1;
            HashSet<Integer>maxNodeConflictSlots  = null;
            for(int j = 0; j < courseCount; j++) {
                HashSet<Integer> neighborSlots = new HashSet<>();
                if (courses.get(j).getTimeSlot() == -1) {
                    HashSet<Course> conflictingCourses = courses.get(j).getConflictingCourses();
                    for(Course conflict: conflictingCourses) {
                        if(conflict.getTimeSlot() != -1) {
                            neighborSlots.add(conflict.getTimeSlot());
                        }
                    }
                    int saturationDegree = neighborSlots.size();
                    if (saturationDegree > maxSaturation || (saturationDegree == neighborSlots.size() && courses.get(j).getConflictCount() > courses.get(maxNode).getConflictCount())) {
                        maxSaturation = saturationDegree;
                        maxNode = j;
                        maxNodeConflictSlots = neighborSlots;
                    }
                }
            }
            int slot = 0;
            Course maxDegreeCourse =  courses.get(maxNode);
            HashSet<Course> conflictingCourses = courses.get(maxNode).getConflictingCourses();
            while(maxDegreeCourse.getTimeSlot() == -1) {
                if(maxNodeConflictSlots.contains(slot)) {
                   slot++;
                } else {
                    maxDegreeCourse.setTimeSlot(slot);
                    if(slot == slotCount)
                        slotCount++;
                }
            }
        }
        return slotCount;
    }


}
