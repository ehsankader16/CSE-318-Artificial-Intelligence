import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class VAH5 implements VariableOrderHeuristic{
    @Override
    public Variable getNextVariable(LatinSquare latinSquare) {
        ArrayList<Variable> unassignedVariables = latinSquare.getUnassignedVariables();
        Variable nextVariable = null;
        int size = unassignedVariables.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for(Variable v : unassignedVariables)
        {
            if (i == item) {
                nextVariable = v;
            }
            i++;
        }
        return nextVariable;
    }
}
