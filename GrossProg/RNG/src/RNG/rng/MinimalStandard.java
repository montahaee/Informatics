package RNG.rng;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Montahaee
 * @version 1.0
 * @created 08-Oct-2022 5:38:29 PM
 */
public class MinimalStandard extends LCG  {

	public MinimalStandard() {
		this.seed = BigInteger.ONE;
		this.multiplier = new BigInteger("16807");
		this.increment = BigInteger.ZERO;
		this.modulus = BigDecimal.valueOf(Math.pow(2.0,31) - 1).toBigInteger();
	}

	/**
	 * @return The clone operation returns one of the LCG types(subclasses).
	 */
	@Override
	public LCG clone() {
		return new MinimalStandard();
	}


//	public MinimalStandard(RandomSequence wrapper) {
//		super(wrapper);
//	}
//
//	/**
//	 *
//	 * @param rv1
//	 * @param rv2
//	 */
//	public double cdf(double rv1, double rv2){
//		return 0;
//	}
//
//	/**
//	 *
//	 * @param rv
//	 */
//	public double cdf(double rv){
//		return 0;
//	}
//
//	/**
//	 *
//	 * @param rvs
//	 */
//	public double exv(List<Integer> rvs){
//		return 0;
//	}
//
//	/**
//	 *
//	 * @param rv
//	 */
//	public double pdf(double rv){
//		return 0;
//	}
//
//	/**
//	 *
//	 * @param rv
//	 */
//	public double pmf(int rv){
//		return 0;
//	}
//
//	/**
//	 *
//	 * @param multiplier
//	 * @param moddulus
//	 * @param incriment
//	 * @param seed
//	 */
//	public int random_variable(int multiplier, int moddulus, int incriment, int seed){
//		return 0;
//	}
//
//	public Interval range(){
//		return null;
//	}
//
//	/**
//	 *
//	 * @param random_numbers
//	 */
//	public boolean validated(List<Integer> random_numbers){
//		return false;
//	}
}//end MinimalStandard