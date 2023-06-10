package CRS.Multiplications;

public interface Multiplications<L, R> {

    /**
     *
     * @param left operand
     * @param right operand
     * @return result of production of the two operands.
     */
    L multiply(L left, R right);
}
