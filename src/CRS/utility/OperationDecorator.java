package CRS.utility;

import java.util.function.Supplier;

public class OperationDecorator<L,R> implements Operation<L,R> {

    private Operation<L,R> operation;

    public OperationDecorator(Operation<L,R> op) {
        this.operation = op;
    }
    public Supplier<L> operate(Pair<L, R> p, L val) {
        return operation.operate(p,val);
    }

    public void setOperation(Operation<L,R> newOperation){
        this.operation = newOperation;
    }
}
