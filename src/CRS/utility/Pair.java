package CRS.utility;


/**
 * @author Montahaee
 * @version 1.0
 * @created 09-Aug-2022 10:01:22 AM
 */
public class Pair<L, R> {

	private final L l;
	private final R r;

	/**
	 * 
	 * @param that_l delegates left side
	 * @param that_r delegates right side
	 */
	public Pair(L that_l, R that_r){
		this.l = that_l;
		this.r = that_r;
	}

	public L getX(){
		return this.l;
	}

	public R getY(){
		return this.r;
	}

	/**
	 * 
	 * @param p current pair to swap
	 */
	public Pair<R, L> swap_operand(Pair<L, R> p){

		return new Pair<>(p.getY(), p.getX());
	}


}//end Pair