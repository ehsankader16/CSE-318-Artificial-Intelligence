package input_processes;

import java.util.ArrayList;
import java.util.HashSet;

//write class for process_classes.Graph in java that has nodes and adjacency list
public class Graph {
    private int V;
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    Graph(ArrayList<Course> courses, ArrayList<Student>students) {
        this.V = courses.size();
        this.courses = courses;
        this.students = students;

    }

    public void dfs(Course currentCourse, int neighborSlot) {
        currentCourse.setVisited(true);
        for(Course neighborCourse: currentCourse.getConflictingCourses()) {
            if(!neighborCourse.isVisited() && neighborCourse.getTimeSlot() == neighborSlot) {
                dfs(neighborCourse, currentCourse.getTimeSlot());
            }
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    //write method to calculate no of different time slots
    public int getTimeSlotCount() {
        HashSet<Integer>timeSlots = new HashSet<>();
        for(Course course : courses) {
            timeSlots.add(course.getTimeSlot());
        }
//        for(Integer a:timeSlots) {
//            System.out.print(a+ " ");
//        }
//        System.out.println();
        return timeSlots.size();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
