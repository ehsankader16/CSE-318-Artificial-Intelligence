import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File input = new File("data/d-15-01.txt");
        Scanner scn = null;
        try {
            scn = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int N = scn.nextInt();
        int[][] inputLatinSquare = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                inputLatinSquare[i][j] = scn.nextInt();
            }
        }

        LatinSquare latinSquare = new LatinSquare(N, inputLatinSquare);
        System.out.println("Input Latin Square");
        System.out.println(latinSquare);
        LatinSquareSolver latinSquareSolver = new LatinSquareSolver(latinSquare, new VAH3()); //there are 5 VAH (VAH1-VAH5)
        long startTime = System.currentTimeMillis();
        //there are 2 solve() algorithms
        //latinSquareSolver.backtrack();
        latinSquareSolver.backtrackWithFC();
        long endTime = System.currentTimeMillis();
        double duration = (double)(endTime - startTime)/1000;
        System.out.println("Solved Latin Square");
        System.out.println(latinSquare);
        System.out.println("Number of total node: "+latinSquareSolver.getNodeCount());
        System.out.println("Number of backtracks: "+latinSquareSolver.getBacktrackCount());
        System.out.println("Execution time in seconds: " + duration);


    }
}
