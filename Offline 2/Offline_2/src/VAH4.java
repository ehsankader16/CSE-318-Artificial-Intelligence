import java.util.ArrayList;
import java.util.HashSet;

public class VAH4 implements VariableOrderHeuristic{
    @Override
    public Variable getNextVariable(LatinSquare latinSquare) {
        ArrayList<Variable> unassignedVariables = latinSquare.getUnassignedVariables();
        int N = latinSquare.getN();
        int[] rowDegree = new int[N];
        int[] columnDegree = new int[N];
        for (Variable v : unassignedVariables) {
            rowDegree[v.getRow()]++;
            columnDegree[v.getColumn()]++;
        }

        int smallestDomainSize = Integer.MAX_VALUE;
        int smallestDomainDegree = 1;
        Variable nextVariable = null;
        for (Variable v : unassignedVariables) {
            int variableDegree = rowDegree[v.getRow()] + columnDegree[v.getColumn()] - 2;
            if ((double)v.getDomainSize()/variableDegree < (double)smallestDomainSize/smallestDomainDegree) {
                smallestDomainSize = v.getDomainSize();
                smallestDomainDegree = variableDegree;
                nextVariable = v;
            }
        }
        return nextVariable;
    }
}
