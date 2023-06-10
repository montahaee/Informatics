package GroProOnlinestrategiespiel.utility;

import java.util.ArrayList;
import java.util.List;

public class CompoundArithmetic<S extends Number>  {
    private final List<Arithmetic<S>> arithmetics;

    public CompoundArithmetic() {
        this.arithmetics = new ArrayList<>();
    }

    public void insert(Arithmetic<S> arithmetic) {
        this.arithmetics.add(arithmetic);
    }

    public List<Arithmetic<S>> getArithmetics() {
        return this.arithmetics;
    }

    //    /**
//     * @return
//     */
//    @Override
//    public Number none() {
//        return null;
//    }
//
//    /**
//     * @param ls
//     * @param rs
//     * @return
//     */
//    @Override
//    public Number add(Number ls, Number rs) {
//        Number s = new Shape<Number,Number>();
//        for (Arithmetic arithmetic : arithmetics) {
//            arithmetic.add(ls,rs);
//        }
//        r
//    }
}
