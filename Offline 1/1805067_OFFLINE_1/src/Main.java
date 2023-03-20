import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[][] board = new int[k][k];

        for (int i=0; i<k; i++) {
            for (int j = 0; j < k; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        Node initialNode = new Node(board, 0,null);

        if (!isSolvable(initialNode)) {
            System.out.println("Initial Board is unsolvable");
            return;
        }
        System.out.println("Initial Board is solvable");
        solveNPuzzle(initialNode, "Hamming");
        solveNPuzzle(initialNode, "Manhattan");

    }

    private static void solveNPuzzle(Node initialNode, String heuristic) {
        PriorityQueue<Node> pq;
        if(heuristic.equalsIgnoreCase("hamming")) {
            pq = new PriorityQueue<>(new HammingComparator());
        } else {
            pq = new PriorityQueue<>(new ManhattanComparator());
        }
        HashSet<Node> closed = new HashSet<>();
        int exploredNode = 0, expandedNode = 0;
        pq.add(initialNode);
        expandedNode++;
        Node currentNode = initialNode;

        while(!pq.isEmpty()) {
            currentNode = pq.poll();
            exploredNode++;
            System.out.println("Cost in current state using " + heuristic + " is " + currentNode.getCost());
            System.out.println(currentNode);

            if(Arrays.deepEquals(currentNode.getBoard(), currentNode.getGoalBoard())) {
                System.out.println("Current state is goal state.");
                System.out.println("Cost using " + heuristic + " is "+currentNode.getCost());
                System.out.println("Explored Node: " + exploredNode + " Expanded Node: "+ expandedNode + "\n");
                break;
            }
            closed.add(currentNode);
            for(Node node:currentNode.getNeighborNodes()) {
                if(!closed.contains(node)) {
                    pq.add(node);
                    expandedNode++;
                }
            }

        }

    }

    private static boolean isSolvable(Node node) {
        int[][] board = node.getBoard();
        int k = board.length;
        int blankRow = node.getBlankRow();
        int line[] = new int[k * k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++)  {
                line[i * k + j] = board[i][j];
            }
        }

        int inversionCount = 0;
        for (int i = 0; i < k * k; i++) {
            if (line[i] == 0)
                continue;
            for (int j = i ; j < k * k; j++) {
                if (line[j] == 0)
                    continue;
                if (line[i] > line[j])
                    inversionCount++;
            }
        }
        System.out.println(inversionCount);
        int blankRowFromLast = (k - 1) - blankRow + 1;
        if (k % 2 == 0) {
            return (inversionCount % 2 == 0 && blankRowFromLast % 2 != 0) || (inversionCount % 2 != 0 && blankRowFromLast % 2 == 0);
        } else {
            return inversionCount % 2 == 0;
        }
    }
}
