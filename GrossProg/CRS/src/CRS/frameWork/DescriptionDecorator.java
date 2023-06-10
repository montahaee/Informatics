package CRS.frameWork;


/**
 * The base decorator delegate {@link #content(CRS)} to the wrapped object.
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class DescriptionDecorator implements Description {

	private final Description wrapping;


	/**
	 * 
	 * @param description is for referencing wrapped object.
	 */
	public DescriptionDecorator(Description description){
		this.wrapping = description;
	}

	/**
	 *
	 * @param crs is nonnull object to the store the sparse matrix optimally.
	 */
	public String content(CRS crs){

	return 	this.wrapping.content(crs);
	}
}//end DescriptionDecorator