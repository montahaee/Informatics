package CRS.Additions;


public class ScalarAddition implements Additions<Double> {

///
///    /**
//     * @return the mathematical identity property of addition
//     * wich around scalar '0' and by matrix zero matrix
//     */
//    @Override
//    public Double identity() {
//        return 0.0;
//    }

    /**
     * @param left  is left operand
     * @param right is right operand
     * @return sum of the two operands as
     */
    @Override
    public Double add(Double left, Double right) {
///        double sum = identity();
//        sum += left + right;
        return left + right;
    }
}
