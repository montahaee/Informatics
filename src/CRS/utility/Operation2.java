package CRS.utility;

import java.util.function.Supplier;

public interface Operation2<L,R> {

    Supplier<L> operation(Pair<L,R> p, L val) ;

    public L operation(Pair<L,R> p);
}
