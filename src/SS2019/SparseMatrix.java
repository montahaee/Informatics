package SS2019;


/**
 * @author Montahaee
 * @version 1.0
 * @created 29-Apr-2022 11:35:47 AM
 */
public class SparseMatrix {

	private final int[] ia;
	private final int[] ja;
	private final double[] wa;
//	private final int length;
//	int


	/**
	 * 
	 * @param numberNonzero to construct a sparse matrix
	 */
	public SparseMatrix (int numberNonzero, int n){
		this.ia = new int[numberNonzero+1];
		this.ja =  new int[numberNonzero];
		this.wa = new double[numberNonzero];
//		this.length = n; not necessary
	}

	public SparseMatrix (int[] idxA, int[] jdxA, double[] valA, int n) {
		this(jdxA.length, n);
		for (int i = 0; i< jdxA.length; i++) {
			this.ia[i] = idxA[i];
			this.ja[i] = jdxA[i];
			this.wa[i] =  valA[i];
		}
		this.ia[jdxA.length] = idxA[jdxA.length];
	}

	public int getLength() {
//		return length;
		return ia[ja.length];
	}

	/**
	 * Print the sparse matrix as dense matrix
	 * i.e. with all zeros.
	 * Assuming each row of the matrix has at least
	 * one nonzero value, as the matrix is supposed
	 * to be non-singular, we can use the following algorithm.
	 */
	public void printAsDense(){
		double[][] dense = new double[ia[ja.length]][ia[ja.length]];
		for (int i = 0; i < ja.length; i++) {
			dense[ia[i]][ja[i]] = wa[i];
		}
//		String matrix = "[";
		for (double[] doubles : dense) {
//			for (int j = 0; j < dense[i].length; j)
			for (double d : doubles) {
				System.out.print(d);
			}
			System.out.println();
		}
	}
}//end SS2019