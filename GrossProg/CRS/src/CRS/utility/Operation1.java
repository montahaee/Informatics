package CRS.utility;

import java.util.function.Supplier;

public class Operation1<L,R> {

//    private final Supplier<? extends Pair<L,R>> ctor;
//    private final Supplier<? extends Pair<L,R>> ctor;
//    private Pair<L,R> field;
//
//    public Operation1 (Supplier<Pair<L,R>> ctor) {
//        this.ctor = Objects.requireNonNull(ctor);
//    }
    public  Supplier<L> operation(Pair<L,R> p, L val) {
        return p::getX;
    }
    public L operation(Pair<L,R> p) {
        return p.getX();
    }


//    public void operate() {
//        this.field = ctor.get();
//    }

}
