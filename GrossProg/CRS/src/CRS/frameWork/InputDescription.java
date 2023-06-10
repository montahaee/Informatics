package CRS.frameWork;


/**
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class InputDescription extends DescriptionDecorator {
//public class InputDescription extends  BaseDescription{

//	private final double[][] right;

	/**
//	 * @param description is for referencing wrapped object.
	 */
	public InputDescription(Description description) {
		super(description);
//	public InputDescription(Description description, double[][] mat) {
//	public InputDescription(double[][] mat) {
////		super(description);
//		right = new double[mat.length][mat.length];
//		for (int i = 0; i < mat.length; i++) {
//			System.arraycopy(mat[i], 0, right[i], 0, mat.length);
//		}
	}

	/**
	 *
	 * @param crs is nonnull object to the store the sparse matrix optimally.
	 */
	public String  content(CRS crs){
//		Using initialization of base description comes left operand by wrapping.
		String l_crs = super.content(crs);// Description left = new BaseDescription().content(crs);
		StringBuilder input = new StringBuilder();
//		CRS right = new CRS(this.right);
//		content();
		return null;
	}

	private String content(CRS l_crs, CRS r_crs) {
//		String ls = content(l_crs);
		return "example" + content(l_crs) +
				content(r_crs);
	}

}//end InputDescription