package input_processes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor {
    private HashMap<Integer, Course> courses;
    private ArrayList<Student> students;
    public FileProcessor(String courseFileName, String studentFileName) {
        this.courses = new HashMap<>();
        this.students = new ArrayList<>();
        this.processCourseFile(courseFileName);
        this.processStudentFile(studentFileName);
    }

    private void processCourseFile(String courseFileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(courseFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            courses.put(Integer.parseInt(input[0]), new Course(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
    }

    private void processStudentFile(String courseFileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(courseFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int studentID = 1;
        while(scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            Student student = new Student(studentID);
            ArrayList<Course> courseList = new ArrayList<>();
            for(int i = 0; i < input.length; i++) {
                courseList.add(courses.get(Integer.parseInt(input[i])));
                student.addEnrolledCourse(courses.get(Integer.parseInt(input[i])));
            }

            setConflicts(courseList);
            students.add(student);
            studentID++;
        }
    }

    private void setConflicts(ArrayList<Course> courseList) {
        int courseListSize = courseList.size();
        for(int i = 0; i < courseListSize; i++) {
            for(int j = 0; j < courseListSize; j++) {
                if(i == j)
                    continue;
                courseList.get(i).addConflictingCourse(courseList.get(j));
            }
        }
    }

    public ArrayList<Course> getCourseList() {
        return new ArrayList<Course>(courses.values());
    }
    public ArrayList<Student> getStudentList() {
        return students;
    }

}
