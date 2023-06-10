package CRS.utility;

public interface OperationStrategy<L,R> {

    /**
     *
     * @param p pair with diverse typs of left and right side
     * @return true if the left and right operands can be operated together.
     */
    boolean isCapable(Pair<L,R> p);

    /**
     *
     * @param p reconstruct left and right side operands to operate
     * @return the result of the operation with an instance of
     * left side operand which can be latter swapped.
     */
    L operate(Pair<L,R> p);
}
