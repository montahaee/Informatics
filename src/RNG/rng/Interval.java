package RNG.rng;


/**
 * @author Montahaee
 * @version 1.0
 * @created 08-Oct-2022 5:38:29 PM
 */
public class Interval {

	private final double upper;
	private final double lower;

	public Interval(){
		this (0,0);
	}

	/**
	 * Instantiate a closed interval [lower bound, upper bound].
	 * @param that_lower is mathematically instantiated as lower bound in a closed interval.
	 * @param that_upper is mathematically instantiated as upper bound in a closed interval.
	 */
	public Interval(double that_lower, double that_upper){
		if (Double.isNaN(that_lower) || Double.isNaN(that_upper)) {
			throw new IllegalArgumentException("The boundaries are not number.");
		}
		if (Double.isInfinite(that_lower) || Double.isInfinite(that_upper)) {
			throw new IllegalArgumentException("The Interval must be assumed closed." +
					" Therefore the boundaries cannot be infinite.");
		}
		if (that_upper < that_lower) {
			throw new IllegalArgumentException("The upper boundary is always greater than or equals the lower one.");
		}
		this.lower = that_lower;
		this.upper = that_upper;
	}

	/**
	 * 
	 * @param x a number
	 * @return  {@code true} if this interval contains the number {@code x}
	 */
	public boolean contains(double x){
		return (this.lower <= x && x <= upper);
	}

	/**
	 *
	 * @return the length of this interval.
	 */
	public double length(){
		return this.upper - this.lower;
	}

	/**
	 *
	 * @return the max value in this interval.
	 */
	public double upper_bound(){
		return this.upper;
	}

	/**
	 *
	 * @return the min value in this interval.
	 */
	public double lower_bound(){
		return this.lower;
	}

	public Interval intersection(Interval other) {
		double min, max;
		min = Math.min(this.lower, other.lower);
		max = Math.max(this.upper, other.upper);
		return new Interval(min, max);
	}

	/**
	 *
	 * @return a simple string representation of this interval.
	 */
	public String toString(){
		return "[" + this.lower + ", " + this.upper + "]";
	}
}//end Interval