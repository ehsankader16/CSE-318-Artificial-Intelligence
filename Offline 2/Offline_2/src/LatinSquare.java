import java.util.ArrayList;


public class LatinSquare {
    private int N;
    private Variable[][] latinSquare;
    private ArrayList<Variable> unassignedVariables;

    LatinSquare(int N, int[][] inputLatinSquare) {
        this.N = N;
        this.latinSquare = new Variable[N][N];
        this.unassignedVariables = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.latinSquare[i][j] = new Variable(inputLatinSquare[i][j], i, j, N);
                if (inputLatinSquare[i][j] == 0) {
                    this.unassignedVariables.add(this.latinSquare[i][j]);
                }
            }
        }
        for (Variable var : this.unassignedVariables) {
            int row = var.getRow();
            int column = var.getColumn();
            for (int i = 0; i < N; i++) {
                if (inputLatinSquare[row][i] != 0) {
                    var.removeFromDomain(inputLatinSquare[row][i]);
                }
                if (inputLatinSquare[i][column] != 0) {
                    var.removeFromDomain(inputLatinSquare[i][column]);
                }
            }
        }
    }

    private ArrayList<Variable> modifyDomain(int row, int column, int value) {
        ArrayList<Variable> modifiedVariables = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (latinSquare[row][i].getValue() == 0 ) {
                latinSquare[row][i].removeFromDomain(value);
                modifiedVariables.add(latinSquare[row][i]);
            }
            if (latinSquare[i][column].getValue() == 0) {
                latinSquare[i][column].removeFromDomain(value);
                modifiedVariables.add(latinSquare[i][column]);
            }
        }
        return modifiedVariables;
    }

    public boolean holds(int row, int column, int value) {
        //System.out.println(row+" "+column+" "+value);
        for (int i = 0; i < N; i++) {
            if (this.latinSquare[row][i].getValue() == value) {
                return false;
            }
            if (this.latinSquare[i][column].getValue() == value) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Variable> setValue(Variable variable, int value) {
        this.unassignedVariables.remove(variable);
        int row = variable.getRow();
        int column = variable.getColumn();
        this.latinSquare[row][column].setValue(value);
        ArrayList<Variable> modifiedVariables = modifyDomain(row, column, value);
        return modifiedVariables;
    }

    public void resetValue(Variable variable, int value, ArrayList<Variable> modifiedVariables) {
        this.unassignedVariables.add(variable);
        variable.setValue(0);
        for (Variable v : modifiedVariables) {
            v.addInDomain(value);
        }
    }

    public int getN() {
        return N;
    }


    public Variable[][] getLatinSquare() {
        return latinSquare;
    }

    public void setLatinSquare(Variable[][] latinSquare) {
        this.latinSquare = latinSquare;
    }

    public ArrayList<Variable> getUnassignedVariables() {
        return unassignedVariables;
    }

    public void setUnassignedVariables(ArrayList<Variable> unassignedVariables) {
        this.unassignedVariables = unassignedVariables;
    }

    @Override
    public String toString() {
        String string = "N = " + this.N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                string += this.latinSquare[i][j].getValue() + " ";
            }
            string += "\n";
        }
        return string;
    }
}
