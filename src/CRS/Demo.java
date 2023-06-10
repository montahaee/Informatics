package CRS;


import CRS.frameWork.BaseDescription;
import CRS.frameWork.CRS;
import CRS.frameWork.InputDescription;
import CRS.utility.Add;
import CRS.utility.Context;
import CRS.utility.Operation;

import javax.swing.*;
import java.util.Map;

/**
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class Demo {


//	private static Map<Integer, Operation>

	/**
	 * 
	 * @param args is for command line arguments.
	 */
	public static void main(String[] args){
//		TODO: replace dummy later with input from dialog windows as the last one.++++++++++++++++++++++++++++++++++++++
//		First preparation of operands  to operate them with some operation
		String lt = JOptionPane.showInputDialog("Enter (s/m) to decide kind of left side operand to operator");
		String rt = JOptionPane.showInputDialog("Enter (s/m) to decide kind of right side operand to operator");
//		OperandType lto = (lt.equals("s") || lt.equals("S")) ? OperandType.SKALAR : OperandType.MATRIX;
//		OperandType rto = (rt.equals("s") || rt.equals("S")) ? OperandType.SKALAR : OperandType.MATRIX;


//      Second preparation respectively  build operation possibility
//		TODO using the following description in the base description or new class to make better dialog window
//		  https://stackoverflow.com/questions/24711264/using-switch-statement-to-provide-action-to-jbuttons-java
		String op = JOptionPane.showInputDialog("Choose the operation '1' for addition otherwise '2'");
		int input = Integer.parseInt(op);
//		if (input == 1 ) {
//			Context<CRS,CRS> context = new Context<>();
//			context.setOperation(new Add<>());
//		}


		String dummy = "";
		BaseDescription description = new BaseDescription(dummy);
		dummy = description.getMatrix().toString();
		var t = new InputDescription(description);
	}
}//end Demo