package CRS.Additions;

public class DecoratorAddition<T extends Number> implements Additions<T>{

    private final Additions<T> wrapping;

    /**
     *
     * @param that_additions is consumed to decorate the coming objects
     */
    public DecoratorAddition(Additions<T> that_additions) {
        this.wrapping = that_additions;
    }

///    /**
//     * @return the mathematical identity property of addition
//     * wich around scalar '0' and by matrix zero matrix
//     */
///    @Override
///    public T identity() {
//       return this.wrapping.identity();
//    }

    /**
     * @param left  is left operand
     * @param right is right operand
     * @return sum of the two operands as
     */
    @Override
    public T add(T left, T right) {
        return this.wrapping.add(left, right);
    }
}
