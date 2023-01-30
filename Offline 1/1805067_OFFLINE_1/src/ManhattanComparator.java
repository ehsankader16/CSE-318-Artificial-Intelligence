import java.util.Comparator;

public class ManhattanComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getCost() + o1.getManhattanCost(), o2.getCost() + o2.getManhattanCost());
    }
}
