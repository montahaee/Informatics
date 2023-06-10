package CRS.Additions;


/**
 * Notice: we could make completely generic operation like
 * @see GroProOnlinestrategiespiel.utility.Arithmetic
 * But the focous is here only of the two variants CRS and double
 * for each operation Add and {@link CRS.Multiplications.Multiplications}.
 * @param <T> is template symbole
 */
public interface Additions<T> {

///    /**
//     *
//     * @return the mathematical identity property of addition
//     * wich around scalar '0' and by matrix zero matrix
//     */
//    T identity();

    /**
     *
     * @param left is left operand
     * @param right is right operand
     * @return sum of the two operands as
     */
    T add(T left, T right);

}
