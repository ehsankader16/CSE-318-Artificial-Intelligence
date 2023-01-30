public class VAH1 implements VariableOrderHeuristic{
    @Override
    public Variable getNextVariable(LatinSquare latinSquare) {
        int smallestDomainSize = Integer.MAX_VALUE;
        Variable nextVariable = null;
        for (Variable v : latinSquare.getUnassignedVariables()) {
            if (v.getDomainSize() < smallestDomainSize) {
                smallestDomainSize = v.getDomainSize();
                nextVariable = v;
            }
        }
        return nextVariable;
    }
}
