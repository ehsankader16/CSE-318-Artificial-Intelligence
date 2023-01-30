import java.util.HashSet;

public class Variable {
    private HashSet<Integer> domains;
    private int value;
    private int row;
    private int column;
    private int N;

    public Variable(int value, int row, int column, int N) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.N = N;
        if(value == 0) {
            this.domains = new HashSet<>();
            for(int i = 0; i < N; i++) {
                this.domains.add(i+1);
            }
        }
    }

    public HashSet<Integer> getDomains() {
        return domains;
    }

    public int getDomainSize() {
        if(this.domains == null) {
            return 0;
        }
        return this.domains.size();
    }

    public void addInDomain(int newDomain) {
        this.domains.add(newDomain);
    }

    public void removeFromDomain(int oldDomain) {
        this.domains.remove(oldDomain);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }




}
