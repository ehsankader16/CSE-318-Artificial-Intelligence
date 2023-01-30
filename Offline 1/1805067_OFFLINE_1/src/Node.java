import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Node {
    private int k;
    private int[][] board;
    private int cost;
    private Node previousNode;
    private int[][] goalBoard;
    private int blankRow;
    private int blankColumn;

    public Node(int[][] board, int cost, Node previousNode) {
        this.k = board.length;
        this.board = board;
        this.previousNode = previousNode;
        this.cost = cost;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (board[i][j] == 0) {
                    this.blankRow = i;
                    this.blankColumn = j;
                }
            }
        }

        this.goalBoard = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                this.goalBoard[i][j] = i * k + j + 1;
            }
        }
        this.goalBoard[k - 1][k - 1] = 0;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCost() {
        return cost;
    }

    public int[][] getGoalBoard() {
        return goalBoard;
    }

    public int getBlankRow() {
        return blankRow;
    }


    public int getBlankColumn() {
        return blankColumn;
    }

    int getHammingCost() {
        int hammingCost = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if ((board[i][j] != goalBoard[i][j]) && (board[i][j] != 0)) {
                    hammingCost++;
                }
            }
        }
        return hammingCost;
    }

    int getManhattanCost() {
        int manhattanCost = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if(i == blankRow && j == blankColumn) {
                    continue;
                } else {
                    int rowCost = Math.abs((board[i][j] - 1) / k - i);
                    int columnCost = Math.abs((board[i][j] - 1) % k - j);
                    manhattanCost += (rowCost + columnCost);
                }
            }
        }
        return manhattanCost;
    }

    public List<Node> getNeighborNodes() {
        List<Node> neighborNodes = new ArrayList<>();
        int temp;
        if (blankRow - 1 > -1) {
            int[][] upBoard = new int[k][];
            for(int i = 0; i < k; i++)
                upBoard[i] = board[i].clone();
            temp = upBoard[blankRow][blankColumn];
            upBoard[blankRow][blankColumn] = upBoard[blankRow - 1][blankColumn];
            upBoard[blankRow - 1][blankColumn] = temp;
            Node upNode = new Node(upBoard, this.cost + 1, this);
            if (!Objects.equals(previousNode, upNode))
                neighborNodes.add(upNode);
        }

        if (blankRow + 1 < k) {
            int[][] downBoard = new int[k][];
            for(int i = 0; i < k; i++)
                downBoard[i] = board[i].clone();
            temp = downBoard[blankRow][blankColumn];
            downBoard[blankRow][blankColumn] = downBoard[blankRow + 1][blankColumn];
            downBoard[blankRow + 1][blankColumn] = temp;
            Node downNode = new Node(downBoard, this.cost + 1, this);
            if (!Objects.equals(previousNode, downNode))
                neighborNodes.add(downNode);
        }

        if (blankColumn - 1 > -1) {
            int[][] leftBoard = new int[k][];
            for(int i = 0; i < k; i++)
                leftBoard[i] = board[i].clone();
            temp = leftBoard[blankRow][blankColumn];
            leftBoard[blankRow][blankColumn] = leftBoard[blankRow][blankColumn - 1];
            leftBoard[blankRow][blankColumn - 1] = temp;
            Node leftNode = new Node(leftBoard, this.cost + 1, this);
            if (!Objects.equals(previousNode, leftNode))
                neighborNodes.add(leftNode);
        }

        if (blankColumn + 1 < k) {
            int[][] rightBoard = new int[k][];
            for(int i = 0; i < k; i++)
                rightBoard[i] = board[i].clone();
            temp = rightBoard[blankRow][blankColumn];
            rightBoard[blankRow][blankColumn] = rightBoard[blankRow][blankColumn + 1];
            rightBoard[blankRow][blankColumn + 1] = temp;
            Node rightNode = new Node(rightBoard, this.cost + 1, this);
            if (!Objects.equals(previousNode, rightNode))
                neighborNodes.add(rightNode);
        }
        return neighborNodes;
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.deepEquals(this.board, ((Node) obj).getBoard());
    }

    @Override
    public String toString() {
        String boardStateString = "";
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                boardStateString += board[i][j] + " ";
            }
            boardStateString += "\n";
        }
        return boardStateString;
    }
}
