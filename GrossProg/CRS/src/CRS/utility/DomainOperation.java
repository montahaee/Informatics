package CRS.utility;

public abstract class DomainOperation<L, R> {

    Operator operator;

    public DomainOperation(Operator that_operator) {
        this.operator = that_operator;
    }

    abstract L add(Pair<L, R> pair);
    abstract L multiply(Pair<L, R> pair);
}
