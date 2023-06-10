package CRS.utility;


import CRS.frameWork.CRS;

import java.util.ArrayList;
import java.util.List;

/**
 * This class {@code Additions } is independent on which the matrix is
 * non-singular as required.
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
//public abstract class Additions<L extends CRS , R extends CRS, I extends Number> implements Operation<CRS,R> {
//public class Additions<L extends CRS , R extends CRS, I extends Number> implements Operation<CRS,R> {
//public class Additions<L, R> extends OperationDecorator<L,R> {
public class Add<L, R>  implements OperationStrategy<L,R> {
//	public Additions(Operation<L, R> op) {
//		super(op);
//	}
//public class Additions<L, R> implements Operation<L,R> {
//public class Additions<L extends CRS , R extends CRS> implements Operation<CRS,R> {
//	private final Supplier<? extends CRS> ctor;
//	private CRS field;
//	Additions(Supplier<L> ctor) {
//		this.ctor = Objects.requireNonNull(ctor);
//	}
//	public void operate() {
//		field = ctor.get();
//	}
///	/**
//	 * @return additive/multiplicative identity(0/1)
//	 */
///	@Override
//	public abstract I identity();
//	private Operation<Double,Double> scalar;

	/**
	 *
	 * @param p realize left- and right-hand side as operands for an operator.
	 */
//	@Override
//	public CRS add(Operation<CRS,CRS> operation){

	public  CRS mat_operate(Pair<CRS , CRS> p){
//	public L operate(Pair<L , R> p){
//	public CRS operate(Pair<CRS, R> p, OperandType type){
//		if (type == OperandType.SKALAR) {
//			throw new ArithmeticException("Addition a scalar number to a matrix is " +
//					"not algebraically well defined!");
//		}
		if (p.getX().getMat_size() != p.getY().getMat_size()) {
			throw new IllegalArgumentException("Addition two matrices with different size " +
					"not algebraically well defined!");
		}
//		I sum = identity();
		int n = p.getX().getMat_size();
		int[] ic = new int[n + 1];
		List<Integer> ojc = new ArrayList<>();
		List<Double> owc = new ArrayList<>();
		int[] il = p.getX().getRow_ptr();
		int[] jl = p.getX().getCol_indices();
		double[] wl = p.getX().getNonzero_values();
		int[] ir = p.getY().getRow_ptr();
		int[] jr = p.getY().getCol_indices();
		double[] wr = p.getY().getNonzero_values();
//		int mj = Math.max(jl.length, jr.length);

//		We know the length of future array 'jc' cannot be greater the sum of 'jr' and 'jb'
//		together which is still less than 'n*n' size of the square matrix. This because of
//		corresponding nonzero elements in the both matrix with less than half size of them.

//		The matrix has at least one nonzero element.
		ic[0] = 0;
		for (int m = 0; m <n; m++){
//			int min_idx = Math.min(il[m], ir[m]);
			int c;

//			The same row start index for left and right matrix
			if (il[m] <= ir[m]) {
//				for (int k = 0; k < n*(m + 1) - il[m]; k++){
				for (c = 0; c <= n - il[m] && wl[il[m] +c] != 0; c++){
//				for (int c = 0; c < n; c++){
//					ic[m] = c + il[m];
//					ic[m] = c + il[m];
//					if(jl[ic[m]] < jr[ic[m]]) {
					if(jl[il[m]] < jr[il[m]]) {

//						ic[m] = c + il[m];
						ojc.add(il[m] + c, jl[il[m]+c]);
						owc.add(il[m] + c, wl[il[m] + c]);
//						k++;
					} else if (jl[il[m]] == jr[il[m]]) { // the same column index
//					} else if (jl[ic[m]] == jr[ic[m]]) { // the same column index
//						ic[m] = c + il[m];
						ojc.add(il[m]+ c, jl[il[m] + c]);
						owc.add(il[m] + c, wl[il[m] + c] + wr[il[m] + c]);
//						k++;
					} else {
//						ic[m] = c + ir[m];
						ojc.add(il[m] + c, jr[il[m] + c]);
						owc.add(il[m] +c, wr[il[m] + c]);
					}
				}
				ic[++m] += c;
//			} else if (il[m] > ir[m]) {
			} else {
				for (c = 0; c <= n - ir[m] && wl[ir[m] +c] != 0; c++){
//					ic[m] = ir[m];
//					ojc.add();
					if(jl[ir[m]] < jr[ir[m]]) {

//						ic[m] = c + il[m];
						ojc.add(ir[m] + c, jl[ir[m]+c]);
						owc.add(ir[m] + c, wl[ir[m] + c]);
//						k++;
					} else if (jl[ir[m]] == jr[ir[m]]) { // the same column index
//					} else if (jl[ic[m]] == jr[ic[m]]) { // the same column index
//						ic[m] = c + il[m];
						ojc.add(ir[m]+ c, jl[ir[m] + c]);
						owc.add(ir[m] + c, wl[ir[m] + c] + wr[ir[m + c]]);
//						k++;
					} else {
//						ic[m] = c + ir[m];
						ojc.add(ir[m] + c, jr[ir[m] + c]);
						owc.add(ir[m], wr[ir[m]]);
					}
				}
				ic[++m] += ++c;
			}
//			for (int k = 0; k < n - il[m]; k++){
//				if (il[k + il[m]] == ir[k + i]) {
//					ic[k]  = il[k];
//					ojc.add(ic[k], jl[k]);
//				} else
//			}
		}
//		int[] jc = new int[ojc.size()];
		int[] jc = ojc.stream().mapToInt(e->e).toArray();
//		Integer[] jc = new Integer[ojc.size()];
		double[] wc = new double[owc.size()];
//		jc = ojc.toArray(jc);
		wc = owc.stream().mapToDouble(e->e).toArray();
//		System.arraycopy(ojc.toArray(),0,jc,0,ojc.size());
//		System.arraycopy(owc,0,wc,0,ojc.size());

//		CRS result = new CRS()
//		if (p.)
//		CRS crs = new CRS(wc, jc, ic, n);
//
//		Additions<CRS,CRS> crs = new Additions<>(CRS::new)
//		L < CRS::new > crs ;
////		return new
//		return new Additions<>(CRS::new(wc, jc, ic, n));
//		return CRS.class.cast(crs);
//		Additions<CRS> crt = new Additions<>(ctor);
//		CRS crs = new Additions<CRS>(CRS::new)
//		return (new Additions<CRS::new> ;
//		L x = new L
//		L crs = new CRS(wc, jc, ic, n);
		return new CRS (wc, jc, ic, n);
	}

//	/**
//	 * @return
//	 */
//	@Override
//	public boolean isFeasible(Pair<L,R> p) {
//		if (p.getX() instanceof  Double && p.getY() instanceof Double) {
//			return true;
//		} else {
//			return p.getX() instanceof CRS && p.getY() instanceof CRS;
//		}
//	}

//	/**
//	 * @param p        realize left- and right-hand side as operands for an operator.
//	 */
//	@Override
//	public Supplier<L> operate(Pair<L, R> p, L val) {
////		L sum;
////		if (p.getX() instanceof  Double && p.getY() instanceof Double) {
////			var t = (double) p.getX() + (double) p.getY();
////
//////			sum =
////		}
//
//		return p::getX;
//	}
//
//	public double add(Pair<L,R> p) {
//		if (p.getX() instanceof  Double && p.getY() instanceof Double) {
//			var t = operate(p,(double)p.getX()+ (double)p.getY());
//			return (double)t.get();
//		}
//		t
//	}
//
//	private Supplier<L> operate(Pair<L,R> p, double v) {
//		var t = operate(p,v);
//		return t;
//	}

	/**
	 * @param p   realize left- and right-hand side as operands for an operator.
//	 * @param val
	 */
//	@Override
//	public <Double> Supplier<Double> operate(Pair<L, R> p, L val) {
//
//		return p::getX;
//	}

//	/**
//	 * @param p   realize left- and right-hand side as operands for an operator.
//	 * @param val
//	 */
//	@Override
//	public <L, R1> Supplier<L1> operate(Pair<L1, R1> p, L1 val) {
//		return null;
//	}

//	/**
//	 * @param p   realize left- and right-hand side as operands for an operator.
//	 * @param val
//	 */
//	@Override
//	public Supplier<L> operate(Pair<L, R> p, L val) {
//		if (p.getX() instanceof Double && p.getY() instanceof Double) {
//			var t = (double)operate(p,(double)p.getX() + (double)p.getY());
//
//			double sum = (double) p.getY() + (double)p.getX();
//
//		}
//	}
//
//	public Double add(double x, double y) {
//		var sum =
//	}

//	public
///	/**
//	 * @param l left-hand side operand
//	 * @param r right-hand side operand
//	 */
///	public void operate(L l, R r) {
//
//	}
//	public double scalar(Pair<Double,Double> p) {
//		var t =  operate(p, p.getX() + p.getY());
//		t.get();
//	}
	public double scalar(Pair<Double,Double> p) {
		return p.getX() + p.getY();
	}

	/**
	 *
	 * @param p pair with diverse typs of left and right side
	 * @return true if the left and right operands can be operated together.
	 */
	@Override
	public boolean isCapable(Pair<L, R> p) {

		return (p.getX().getClass().isInstance(p.getY()));
	}

	/**
	 * @param p
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public L operate(Pair<L, R> p) {
		if (p.getX() instanceof CRS && isCapable(p)) {

//			 var g = (Pair<CRS, CRS>) p.getClass().cast((p));
//			        mat_operate((Pair<CRS, CRS>) p).
//			var lt = Pair.class.cast((CRS)p.getX());
//
//			Pair<CRS,CRS> rt = Pair.class.cast((CRS)p.getY());
//			var res = mat_operate(new Pair<>(lt.getX(),rt.getY()));
//			var dt = mat_operate(Pair.class.cast((CRS)p.getX()))
//
//			Pair<CRS,CRS> cp = mat_operate((Pair<CRS, CRS> p)
//			var t = Pair.class.cast(mat_operate(Pair<CRS, CRS>p))
//			var t = p.getClass().cast(mat_operate(p))
////			p.getX() = (L) mat_operate((Pair<CRS, CRS>) p);

			return (L) mat_operate((Pair<CRS, CRS>) p);
		}
//		el
		return (L) mat_operate((Pair<CRS, CRS>) p);
	}
}//end Additions