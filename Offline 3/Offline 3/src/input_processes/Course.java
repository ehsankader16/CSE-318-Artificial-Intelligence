package input_processes;

import java.util.HashSet;

//process_classes.Course is a node in a graph  of courses
public class Course {
    private int courseID;
    private int totalEnrollment;
    private int timeSlot;
    private int conflictCount;
    private boolean visited;
    private HashSet<Course> conflictingCourses;
    //create constructor and get set methods for all variables
    Course(int courseID, int totalEnrollment) {
        this.courseID = courseID;
        this.totalEnrollment = totalEnrollment;
        this.timeSlot = -1;
        this.conflictCount = 0;
        conflictingCourses = new HashSet<>();
    }
    public int getCourseID() {
        return courseID;
    }
    public int getTotalEnrollment() {
        return totalEnrollment;
    }
    public void addConflictingCourse(Course newConflict) {
        if(this.conflictingCourses.add(newConflict))
            this.conflictCount++;
    }
    public int getTimeSlot() {
        return timeSlot;
    }
    public HashSet<Course> getConflictingCourses() {
        return conflictingCourses;
    }

    public int getConflictCount() {
        return conflictCount;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public void setTotalEnrollment(int totalEnrollment) {
        this.totalEnrollment = totalEnrollment;
    }
    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    //write setVisited and isVisited methods
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean isVisited() {
        return visited;
    }

    public void printConflictingCourse() {
        System.out.print(courseID+": "+conflictingCourses.size()+" "+conflictCount);
//        for(process_classes.Course course: conflictingCourses) {
//            System.out.print(course.getCourseID()+" ");
//        }
        System.out.println();
    }

}
