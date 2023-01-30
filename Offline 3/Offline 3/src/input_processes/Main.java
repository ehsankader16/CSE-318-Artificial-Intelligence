package input_processes;

import constructive_heuristics.*;
import penalty.*;
import perturbative_heuristics.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String courseFiles[] = {"Toronto/car-f-92.crs", "Toronto/car-s-91.crs", "Toronto/kfu-s-93.crs",
                                "Toronto/tre-s-92.crs", "Toronto/yor-f-83.crs"};
        String studentFiles[] = {"Toronto/car-f-92.stu", "Toronto/car-s-91.stu", "Toronto/kfu-s-93.stu",
                                "Toronto/tre-s-92.stu", "Toronto/yor-f-83.stu"};
        for(int i = 0; i < courseFiles.length; i++) {
            String courseFileName = courseFiles[i];
            String studentFileName = studentFiles[i];
            System.out.println("Input files:" + courseFileName + " " + studentFileName);
            FileProcessor fileProcessor = new FileProcessor(courseFileName, studentFileName);

            ArrayList<Course> courses = fileProcessor.getCourseList();
            ArrayList<Student> students = fileProcessor.getStudentList();

            Graph graph = new Graph(courses, students);
            Penalty penalty = new ExponentialPenalty();
//            Penalty penalty = new LinearPenalty();

//            LargestDegreeHeuristic largestDegreeHeuristic = new LargestDegreeHeuristic();
//            largestDegreeHeuristic.schedule(graph.getCourses());

            SaturationDegreeHeuristic saturationDegreeHeuristic = new SaturationDegreeHeuristic();
            saturationDegreeHeuristic.schedule(graph.getCourses());

//            LargestEnrollmentHeuristic largestEnrollmentHeuristic = new LargestEnrollmentHeuristic();
//            largestEnrollmentHeuristic.schedule(graph.getCourses());

//            RandomOrderingHeuristic randomOrderingHeuristic= new RandomOrderingHeuristic();
//            randomOrderingHeuristic.schedule(graph.getCourses());



            System.out.println("After constructive heuristic: "+graph.getTimeSlotCount()+" "+String.format("%.3f",penalty.calculateAveragePenalty(students)));
            KempeChainInterchange kempeChainInterchange = new KempeChainInterchange();
            kempeChainInterchange.runKempe(graph, penalty,1000);
            System.out.println("After Kempe-Chain Interchange heuristic: "+graph.getTimeSlotCount()+" "+String.format("%.3f",penalty.calculateAveragePenalty(students)));
            PairSwapOperator pairSwapOperator = new PairSwapOperator();
            pairSwapOperator.runPairSwap(graph, penalty,1000);
            System.out.println("After Pair Swap Operator: "+graph.getTimeSlotCount()+" "+ String.format("%.3f",penalty.calculateAveragePenalty(students)));
            System.out.println();
        }

    }
}
