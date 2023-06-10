package CRS.utility;

import java.util.function.Supplier;

public class Test2<L,R> implements Operation2<L,R>{
    Operation2<L,R> operation2;

    public Test2(Operation2<L,R> op) {
        this.operation2 = op;
    }
    /**
     * @param p
     * @param val
     * @return
     */
    @Override
    public Supplier<L> operation(Pair<L, R> p, L val) {
        return operation2.operation(p,val);
    }

    /**
     * @param p
     * @return
     */
    @Override
    public L operation(Pair<L, R> p) {
        return null;
    }


}
