package CRS.frameWork;


import java.util.Arrays;

/**
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class BaseDescription implements Description {
	private final StringBuilder matrix;

	/**
	 *
	 * @param component_name is the name of matrix/scalar
	 */
	public BaseDescription(String component_name) {
		this.matrix = new StringBuilder(component_name);
	}
	public BaseDescription() {
		this.matrix = new StringBuilder();
	}

///	@Override
///	public String toString() {
//
//	}

	/**
	 *
	 * @param crs is nonnull object to the store the sparse matrix optimally.
	 */
	public String content(CRS crs){
		String name = matrix.toString().toLowerCase();
		matrix.delete(0,name.length());
		matrix.append('i').append(name).append(":");
		matrix.append(Arrays.toString(crs.getRow_ptr()));
		matrix.append("\n");
		matrix.append('j').append(name).append(":");
		matrix.append(Arrays.toString(crs.getCol_indices()));
		matrix.append("\n");
		matrix.append('w').append(name).append(":");
		matrix.append(Arrays.toString(crs.getNonzero_values()));
		return matrix.toString();
	}

	/**
	 *
	 * @return basic content of the CRS as String(builder)
	 */
	public String getMatrix() {
		return this.matrix.toString();
	}


}//end BaseDescription