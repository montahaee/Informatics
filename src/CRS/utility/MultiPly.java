package CRS.utility;


import CRS.frameWork.CRS;

///**
// * @author Montahaee
// * @version 1.0
// * @created 09-Aug-2022 10:01:22 AM
// */
//public class MultiPly<C extends CRS,S> implements Operation<CRS,S> {
//
/////	private final Supplier<? extends S> ctor;
/////	private S field;
/////	private MultiPly(Supplier<S> ctor){
////		this.ctor = Objects.requireNonNull(ctor);
////	}
//
////	/**
////	 * @return additive/multiplicative identity(0/1)
////	 */
////	@Override
////	public S identity() {
////
////		return null;
////	}
//
//	/**
//	 * @param p realize left- and right-hand side as operands for an operator.
//	 */
//	@Override
//	public CRS operate(Pair<CRS, S> p, OperandType operandType) {
//		if (operandType == OperandType.SKALAR) {
//			for (double element : p.getX().getNonzero_values()) {
//
//				element *= (double) p.getY();
//			}
//			return p.getX();
//		}
//		CRS l_crs = p.getX();
//		CRS r_crs = (CRS) p.getY();
//		r_crs = r_crs.T();
//		if (l_crs.getMat_size() != r_crs.getMat_size()) {
//			throw new ArithmeticException("The both matrices are restricted to be squared" +
//					" and therefore their size must be the same.");
//		}
//		int lp, rp;
//		CRS result = new CRS(l_crs);
//		result.reset();
//		int[] ptr = new int[l_crs.getRow_ptr().length];
//
//		for (lp = 0; lp < l_crs.getNonzero_values().length; ) {
////				get current row of result matrix
//			int cr = l_crs.getRow_ptr()[lp];
//
//			for (rp = 0; rp < r_crs.getNonzero_values().length; ) {
////					current column of result matrix
//				int cc = r_crs.getRow_ptr()[rp];
//
////					temporary pointers
//				int l_temp = lp;
//				int r_temp = rp;
//				int sum = 0;
//
////					search elements with the same row and column value and sum them.
//				while (l_temp < l_crs.getNonzero_values().length && l_crs.getRow_ptr()[l_temp] == cr &&
//						r_temp < r_crs.getNonzero_values().length && r_crs.getRow_ptr()[r_temp] == cc) {
//					if (l_crs.getCol_indices()[l_temp] < r_crs.getCol_indices()[r_temp]) {
//						l_temp++;
//					} else if (l_crs.getCol_indices()[l_temp] > r_crs.getCol_indices()[r_temp]) {
//						r_temp++;
//					} else {
//						sum += l_crs.getNonzero_values()[l_temp++] * r_crs.getNonzero_values()[r_temp];
//					}
//				}
//				if (sum != 0) {
//					result.insert(cr, cc, sum);
//				}
//				while (rp < r_crs.getMat_size() && r_crs.getRow_ptr()[rp] == cc) {
//					rp++;
//				}
//			}
//			while (lp < l_crs.getMat_size() && l_crs.getRow_ptr()[lp] == cr) {
//				lp++;
//			}
//		}
//		return result;
//	}
//			for (int row = 0; row < l_crs.getMat_size(); row++) {
//				for (int col = 0; col < r_crs.getMat_size(); col++) {
//
//				}
//			}

//			int l_current = 0; // current index in left operand.
//			int r_current = 0; // current index in right operand.
//			int t_current = 0; // current temporary index .

//			while (l_current < l_crs.getRow_ptr().length || r_current < r_crs.getRow_ptr().length) {
//				 int alr = l_crs.getRow_ptr()[l_current++];
//			}
//		return null;
//	}

//}//end MultiPly