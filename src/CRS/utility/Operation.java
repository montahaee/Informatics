package CRS.utility;


import java.util.function.Supplier;

/**
 * 'L' is abbreviation of left side for example a Complex object like 'CRS'.
 * 'R' is abbreviation of  right side for example a simple object like 'SKALAR'.
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
//public class Operation<L,R> {
public interface Operation<L,R> {

//	boolean isFeasible(Pair<L,R> p);
//	enum OperandType {
//		SKALAR,
//		MATRIX;
//
//	}

//	/**
//	 *
//	 * @return additive/multiplicative identity(0/1)
//	 */
//	<S extends Double>S identity();

	/**
	 *
	 * @param p realize left- and right-hand side as operands for an operator.
	 */
//	L operate(Pair<L,R> p);
	Supplier<L> operate(Pair<L,R> p, L val);

//	Supplier<L> operate(Pair<L,R> p, L val);
//	C operate(Pair<C,S> p, OperandType type);



///	/**
//	 *
//	 * @param l left-hand side operand
//	 * @param r right-hand side operand
//	 */
//	Object operate(L l, R r);

}