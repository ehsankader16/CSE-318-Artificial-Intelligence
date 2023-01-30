import java.util.ArrayList;
import java.util.Collections;

public class LatinSquareSolver {
    private LatinSquare latinSquare;
    private VariableOrderHeuristic variableOrderHeuristic;
    private int nodeCount;
    private int backtrackCount;


    LatinSquareSolver(LatinSquare latinSquare, VariableOrderHeuristic variableOrderHeuristic) {
        this.latinSquare = latinSquare;
        this.variableOrderHeuristic = variableOrderHeuristic;
        this.nodeCount = 0;
        this.backtrackCount = 0;
    }

    public boolean backtrack() {
        if (this.latinSquare.getUnassignedVariables().size() == 0) {
            return true;
        }
        Variable chosenVariable = this.variableOrderHeuristic.getNextVariable(this.latinSquare);
        if(chosenVariable == null) {
            return false;
        }
        ArrayList domainValues = new ArrayList<>(chosenVariable.getDomains());
        Collections.sort(domainValues);
        int len = domainValues.size();
        int chosenValue = 0;
        for(int i = 0;  i < len; i++) {
            chosenValue = (int)domainValues.get(0);
            domainValues.remove(0);
            this.nodeCount++;
            if (this.latinSquare.holds(chosenVariable.getRow(), chosenVariable.getColumn(), chosenValue)) {
                ArrayList<Variable> modifiedVariables = this.latinSquare.setValue(chosenVariable, chosenValue);
                if (backtrack()) {
                    return true;
                }
                this.latinSquare.resetValue(chosenVariable, chosenValue, modifiedVariables);
            }
        }
        this.backtrackCount++;
        return false;
    }

    //backtrackWithFC -> backtrackWithForwardChecking
    public boolean backtrackWithFC() {
        if (this.latinSquare.getUnassignedVariables().size() == 0) {
            return true;
        }
        Variable chosenVariable = this.variableOrderHeuristic.getNextVariable(this.latinSquare);
        if(chosenVariable == null) {
            return false;
        }
        boolean doBacktrack = true;
        ArrayList domainValues = new ArrayList<>(chosenVariable.getDomains());
        Collections.sort(domainValues);
        int len = domainValues.size();
        int chosenValue = 0;
        for(int i = 0;  i < len; i++) {
            chosenValue = (int)domainValues.get(0);
            domainValues.remove(0);
            this.nodeCount++;
            if (this.latinSquare.holds(chosenVariable.getRow(), chosenVariable.getColumn(), chosenValue)) {
                ArrayList<Variable> modifiedVariables = this.latinSquare.setValue(chosenVariable, chosenValue);
                for (Variable v : modifiedVariables) {
                    if (v.getDomainSize() == 0) {
                        this.latinSquare.resetValue(chosenVariable, chosenValue, modifiedVariables);
                        doBacktrack = false;
                        break;
                    }
                }
                if (doBacktrack) {
                    if (backtrackWithFC()) {
                        return true;
                    }
                    this.latinSquare.resetValue(chosenVariable, chosenValue, modifiedVariables);
                }
            }
        }
        this.backtrackCount++;
        return false;

    }
    public int getBacktrackCount() {
        return backtrackCount;
    }

    public int getNodeCount() {
        return nodeCount;
    }

}
