package CRS.Multiplications;

import CRS.frameWork.CRS;
import CRS.utility.Pair;

public class MixedProduct implements Multiplications<CRS, Double> {

    /**
     * @param left  operand
     * @param right operand
     * @return result of production of the two operands.
     */
    @Override
    public CRS multiply(CRS left, Double right) {
        for (double element : left.getNonzero_values()) {
            element *= right;
        }
        return left;
    }

    public CRS multiply(Double left, CRS right) {
        for (double element : right.getNonzero_values()) {
            element *= left;
        }
        return right;
    }


}
