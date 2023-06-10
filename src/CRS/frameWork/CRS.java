package CRS.frameWork;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class CRS {

	/**
	 * the corresponding indices of the nonzero elements
	 */
	private int[] col_indices;
//	private List<Integer> col_indices;
	/**
	 * the number of the rows in a square matrix
	 */
	private final int mat_size;
	/**
	 * nonzero elements in a square matrix
	 */
	private double[] nonzero_values;
	/**
	 * pointer to the start location of the rows.
	 */
	private int[] row_ptr;

//	public CRS() {
//		this(new double[0],);
//	}

	/**
	 * 
	 * @param matrix should be a sparse matrix and convert it
	 *                 to a CRS object
	 */
	public CRS(double[][] matrix){

//		Check if the matrix is square!
		if (matrix.length != matrix[0].length) {
			throw new  IllegalArgumentException("The matrix\n " + Arrays.toString(matrix) +
					"\n is non-square and therefor it cannot be non-singular!");
		}

		if (isSingular(matrix)) {
			throw new IllegalArgumentException("the square matrix:\n" +
					Arrays.toString(matrix)	+"\n is singular!");
		}

		if (!isSparse(matrix)) {
			throw new IllegalArgumentException("The matrix:\n"
					+ Arrays.toString(matrix) + "\n is  not sparse!");
		}
///		int row;
//		int column;
		int nonzero_count = nonzeroCount(matrix);
//		get number of rows and columns
		this.mat_size = matrix.length;
//		allocate memory of the nonzero elements in the matrix
		nonzero_values = new double[nonzero_count];
		col_indices = new int[nonzero_count];
//		allocate memory of row pointer
		row_ptr = new int[mat_size + 1];
		row_ptr[0] = 0;
//		point to the next nonzero element to store it
		int idx = 0;
		for (int i = 0; i < mat_size; i++) {
			for (int j = 0; j < mat_size; j++) {
				if (matrix[i][j] != 0){
					nonzero_values[idx] = matrix[i][j];
					col_indices[idx++] = j;
				}
			}
//			update row pointer
			row_ptr[i+1] = idx;
		}
	}

	/**
	 * Constructor to set up the values for the arrays and size.
	 * Call this constructor if you already have at hand.
	 *
	 * @param nonzero_vls   Array holding nonzero values
	 * @param col_ids	    Array holding the column indices.
	 * @param that_row_ptr	Array holding the row pointer values.
	 * @param size		Number of row/column in the CRS (square)matrix has.
	 */
	public CRS(double[] nonzero_vls, int[] col_ids, int[] that_row_ptr, int size) {
		this.nonzero_values = new double[nonzero_vls.length];
		this.col_indices = new int[col_ids.length];
		this.row_ptr = new int[that_row_ptr.length];
		System.arraycopy(nonzero_vls, 0,this.nonzero_values,0,nonzero_vls.length);
		System.arraycopy(col_ids, 0,this.col_indices,0,col_ids.length);
		System.arraycopy(that_row_ptr, 0,this.row_ptr,0,that_row_ptr.length);
		this.mat_size = size;
	}

	/**
	 * @implNote to avoid of broking(for example after removing the getter in the class) following
	 * constructor, we use directly access of attributes.
	 * @param matrix as CRS object that will be copied through new CRS matrix class(copy constructor)
	 */
	public CRS(CRS matrix) {
		this.nonzero_values = new double[matrix.nonzero_values.length];
		this.col_indices = new int[matrix.col_indices.length];
		this.row_ptr = new int[matrix.row_ptr.length];
		System.arraycopy(nonzero_values,0, this.nonzero_values,0,matrix.nonzero_values.length);
		System.arraycopy(col_indices,0, this.col_indices,0,matrix.col_indices.length);
		System.arraycopy(row_ptr,0, this.row_ptr,0,matrix.row_ptr.length);
		this.mat_size = matrix.mat_size;

	}

	public boolean insert(int r, int c, double val) {
		if (r > this.mat_size || c > this.mat_size) {
			System.out.println("Wrong entry! Out of bound index.");
			return false;
		}
///		if (getElement(r,c) != 0) {
//			System.out.println("In this position exist already: " + getElement(r,c));
//			return false;
//		}
		if (row_ptr == null) {
//			int size = Math.max(r, c);
			double[][] mat = new double[mat_size][mat_size];
			mat[r][c] = val;
			new CRS(mat);
			return true;
		}
		int[] temp_cols = new int[col_indices.length + 1];
		double[] temp_ws = new double[nonzero_values.length + 1];
//		get start point in the row
		int rs = row_ptr[r];
//		get pointer to the next row start
		int nrs = ++row_ptr[r +1];
		for (int k = rs; k < nrs; k++) {
			if (col_indices[k] > c) {
				System.arraycopy(col_indices,0, temp_cols, 0, k);
				temp_cols[k] = c;
				System.arraycopy(col_indices, k, temp_cols, k +1, temp_cols.length);
				System.arraycopy(nonzero_values, 0, temp_ws,0,k);
				temp_ws[k] = val;
				System.arraycopy(nonzero_values, k, temp_ws, k + 1, temp_ws.length);
				col_indices = new int[temp_cols.length];
				System.arraycopy(temp_cols,0,col_indices,0,temp_cols.length);
				nonzero_values = new double[temp_ws.length];
				System.arraycopy(temp_ws,0,nonzero_values,0,temp_cols.length);
				return true;

			} else if (col_indices[k] < c){
				System.arraycopy(col_indices, 0, temp_cols, 0, k + 1);
				temp_cols[k + 1] = c;
				System.arraycopy(col_indices, k + 1, temp_cols, k + 2, temp_cols.length);

				System.arraycopy(nonzero_values, 0, temp_ws, 0, k + 1);
				temp_ws[k + 1] = val;
				System.arraycopy(nonzero_values, k + 1, temp_ws, k + 2, temp_ws.length);
				col_indices = new int[temp_cols.length];
				System.arraycopy(temp_cols, 0, col_indices, 0, temp_cols.length);
				nonzero_values = new double[temp_ws.length];
				System.arraycopy(temp_ws, 0, nonzero_values, 0, temp_cols.length);
				return true;
			}
		}
		return false;
	}

	/**
	 * a pseudo reset CRS Matrix. This because, the matrix is singular.
	 */
	public void reset() {
//		int nzs = this.mat_size;
//		CRS crs = new CRS(new double[mat_size][mat_size]);
//		this.row_ptr = new int[crs.row_ptr.length];
//		this.col_indices = new  int[crs.col_indices.length];
//		this.nonzero_values = new double[crs.nonzero_values.length];
		this.row_ptr = null;
		this.col_indices = null;
		this.nonzero_values = null;
		new CRS(this);
	}

	/**
	 * 
	 * @param i current row
	 * @param j current column
	 * @return the element in the matrix
	 */
	public double getElement(int i, int j){
//		get start point in the row
		int rs = row_ptr[i];
//		get pointer to the next row start
		int nrs = row_ptr[i +1];
//		search the column indices of the elements in row 'i'
		for (int k = rs; k < nrs; k++) {
			if (col_indices[k] == j) {
				return nonzero_values[k];
			} else if (col_indices[k] > j) {
				return 0;
			}
		}
//		element at the matrix[i][j] is not nonzero.
		return 0;
	}

	/**
	 *
	 * @param mat is square matrix
	 * @return true if the matrix is singular else false.
	 */
	private boolean isSingular(double[][] mat) {
		return (determinant(mat) == 0);
	}


	/**
	 *
	 * @param mat is a square matrix
	 * @return 0.0 if the matrix is singular
	 */
	private double determinant(double[][] mat) {
		return determinant(mat, mat.length);
	}

	private double determinant (double[][] mat, int n) {

		double determinant = 0;
		double[][] temp = new double[mat.length][mat.length];
		int sign = 1;

		for (int k = 0; k <n; k++) {
//			drive minor matrix M_0k
			minor(mat, k,n, temp);
			determinant += sign * mat[0][k] * determinant(temp, n - 1);
			sign *= -1;
		}
		return determinant;
	}

	/**
	 * temporary minor matrix for square matrix 'mat'.
	 * <a href="https://en.wikipedia.org/wiki/Cofactor_(linear_algebra)">cofactor</a>
	 *
	 * @param mat is a square matrix
	 * @param c   is specific column index
	 * @param M   is the current minor matrix
	 */
	private void minor(double[][] mat, int c, int curr, double[][] M) {
		if (!(mat.length == M.length && mat[0].length == M[0].length)) {
			throw new IllegalArgumentException("the size of the input matrix and its" +
					" temporary container must be the same!" );
		}
		int i = 0;
		int j = 0;
		for (int row = 0; row < curr; row++) {
			for (int col = 0; col < curr; col++) {
//				copying element wich are not given in
//				the current column and row.
				if (row != 0 && col != c) {
					M[i][j++] = mat[row][col];

					if (j == mat.length -1) {
						j = 0;
						i++;
					}
				}
			}
		}
	}

	/**
	 *
	 * @return the corresponding indices of the nonzero elements
	 */
	public int[] getCol_indices() {
		return this.col_indices;
	}

	/**
	 *
	 * @return the number of the rows in a square matrix
	 */
	public int getMat_size() {
		return this.mat_size;
	}

	/**
	 *
	 * @return nonzero elements in a square matrix
	 */
	public double[] getNonzero_values() {
		return this.nonzero_values;
	}

	/**
	 *
	 * @return pointer to the start location of the rows.
	 */
	public int[] getRow_ptr() {
		return this.row_ptr;
	}

	/**
	 *
	 * @return the byte size of all nonzero elements of the matrix.
	 */
	public int byte_size() {
		int sum = 0;
		for (double nonzero : nonzero_values) {
			if (nonzero == (int) nonzero) {
				sum += 4;
			} else {
				sum += 8;
			}
		}
		return sum;
	}

	/**
	 *
	 * @return the maximal byte size of a square matrix.
	 */
	public int origin_matrix_size(){
		return 8*(row_ptr.length - 1)*(row_ptr.length -1);
	}

///	/**
//	 *
//	 * @param arr can each attribute listed above
//	 * @return string form of arr.
//	 */
///	String toString(int[] arr) {
////	TODO thinking about the lower case name of the matrix as it required in the problem.
//
//		return Arrays.toString(arr);
//	}

	/**
	 * this can be employed in other class or package.
	 * @return true if the number of the zero elements
	 * in the matrix greater than number of nonzero ones.
	 */
	public boolean isSparse() {
		return 2*nonzero_values.length < (mat_size*mat_size);
	}

	/**
	 * this can be employed to many (CRS) matrix operations(e.g.{@link #<CRS.utility.MultiPly>}.
	 *
	 * @return transpose of current CRS.
	 */
	public CRS T() {
		CRS transpose = new CRS(this);
		int c, idx;
		List<Integer> count = new ArrayList<>();
		for (int i = 0; i < mat_size; i++) {
			for (int j = row_ptr[i]; j < row_ptr[i+1]; j++) {
				count.add(col_indices[j],col_indices[j]++);
			}
		}
		for (int j = 0; j < mat_size; j++) {
			transpose.row_ptr[j + 1] = transpose.row_ptr[j] + count.get(j);
		}
		transpose.row_ptr[mat_size + 1] = transpose.row_ptr[mat_size] + 1;
//		replace all element of count with zero as new initialization.
		count.replaceAll(e-> 0);
		for (int k = 0; k < mat_size; k++) {
			for (int l = row_ptr[k]; l < row_ptr[k+1]; l++) {
				idx = transpose.row_ptr[col_indices[k]] + count.get(col_indices[l]);
				transpose.col_indices[idx] = k;
				transpose.nonzero_values[idx] = nonzero_values[l];
				count.add(col_indices[l], col_indices[l]++);
			}
		}
		return transpose;
	}

	/**
	 *
	 * @param mat is a square matrix
	 * @return true if the number of nonzero elements less than zero.
	 */
	private boolean isSparse(double[][] mat) {
		return 2*nonzeroCount(mat) < (mat.length * mat.length);
	}

	/**
	 *
	 * @param mat is a square matrix
	 * @return the number of nonzero elements.
	 */
	private int nonzeroCount(double[][] mat) {
		int count = 0;
		for (int r = 0; r < this.mat_size; r++) {
			for (int c = 0; c < mat_size; c++) {
				if (mat[r][c] != 0) {
					count++;
				}
			}
		}
		return count;
	}

}//end CRS