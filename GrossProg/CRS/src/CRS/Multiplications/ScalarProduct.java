package CRS.Multiplications;

public class ScalarProduct implements Multiplications<Double,Double>{

    /**
     * @param left  operand
     * @param right operand
     * @return result of production of the two operands.
     */
    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

}
