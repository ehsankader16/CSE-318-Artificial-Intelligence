import java.util.ArrayList;
import java.util.HashSet;

public class VAH3 implements VariableOrderHeuristic{
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
        int smallestDomainDegree = -1;
        Variable nextVariable = null;
        for (Variable v : unassignedVariables) {
            int variableDegree = rowDegree[v.getRow()] + columnDegree[v.getColumn()] - 2;
            if (v.getDomainSize() < smallestDomainSize) {
                smallestDomainSize = v.getDomainSize();
                smallestDomainDegree = variableDegree;
                nextVariable = v;
            } else if(v.getDomainSize() == smallestDomainSize) {
                if (variableDegree > smallestDomainDegree) {
                    nextVariable = v;
                    smallestDomainDegree = variableDegree;
                }
            }
        }
        return nextVariable;
    }
}
