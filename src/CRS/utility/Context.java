package CRS.utility;


import java.util.function.Supplier;

/**
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class Context<S, T> {

	private Operation<S, T> operation;


	/**
	 * 
	 * @param operands consist of CRS and Skalar objects.
	 */
//	public Supplier<S> calculate(Pair<S,T> operands){
//		return operation.operate(operands,operands.getX());
//	}
//	public S calculate(Pair<S,T> operands, Operation.OperandType typ){
//		return operation.operate(operands, typ);
//	}

	/**
	 * 
	 * @param newOperation to execute an arithmetic calculation.
	 */
	public void setOperation(Operation<S,T> newOperation){
		this.operation = newOperation;
	}
}//end Context